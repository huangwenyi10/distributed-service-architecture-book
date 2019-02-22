package com.example.demo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioTest {


    public static void main(String[] args) throws InterruptedException {
        //启动线程，运行服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerBetter.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);

        //启动线程，运行客户端
        char operators[] = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(new Runnable() {
            @SuppressWarnings("static-access")
            @Override
            public void run() {
                while (true) {
                    //随机产生算术表达式
                    String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
                    Client.send(expression);
                    try {
                        Thread.currentThread().sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}

/**
 * 描述：传统BIO通信模型
 * @author ay
 * @date 2019-02-10
 */
//class ServerBetter{
//
//    //默认的端口号
//    private static int DEFAULT_PORT = 12345;
//    /** 单例的ServerSocket **/
//    private static ServerSocket server;
//
//    /** 根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值 **/
//    public static void start() throws IOException {
//        //使用默认值端口
//        start(DEFAULT_PORT);
//    }
//    //这个方法不会被大量并发访问，不太需要考虑效率，直接进行方法同步就行了
//    public synchronized static void start(int port) throws IOException{
//        if(server != null){
//            return;
//        }
//        try{
//            //通过构造函数创建ServerSocket，如果端口合法且空闲，服务端就监听成功
//            server = new ServerSocket(port);
//            System.out.println("服务器已启动，端口号：" + port);
//            //通过无限循环监听客户端连接，如果没有客户端接入，将阻塞在accept操作上。
//            while(true){
//                Socket socket = server.accept();
//                //当有新的客户端接入时，会执行下面的代码
//                //然后创建一个新的线程处理这条Socket链路
//                new Thread(new ServerHandler(socket)).start();
//            }
//        }finally{
//            //一些必要的清理工作
//            if(server != null){
//                System.out.println("服务器已关闭。");
//                server.close();
//                server = null;
//            }
//        }
//    }
//
//}


/**
 * 描述：伪异步I/O编程模型
 * @author ay
 * @date 2019-02-10
 */
class ServerBetter {
    /** 默认的端口号 **/
    private static int DEFAULT_PORT = 12345;
    /** 单例的ServerSocket **/
    private static ServerSocket server;
    /** 线程池 懒汉式的单例 **/
    private static ExecutorService executorService = Executors.newFixedThreadPool(60);
    /** 根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值 **/
    public static void start() throws IOException{
        //使用默认值
        start(DEFAULT_PORT);
    }
    //这个方法不会被大量并发访问，不太需要考虑效率，直接进行方法同步就行了
    public synchronized static void start(int port) throws IOException{
        if(server != null){
            return;
        }
        try{
            //通过构造函数创建ServerSocket
            //如果端口合法且空闲，服务端就监听成功
            server = new ServerSocket(port);
            System.out.println("服务器已启动，端口号：" + port);
            //通过无线循环监听客户端连接
            //如果没有客户端接入，将阻塞在accept操作上。
            while(true){
                Socket socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码
                //从线程池中获取一个新的线程处理这条Socket链路
                executorService.execute(new ServerHandler(socket));
            }
        }finally{
            //一些必要的清理工作
            if(server != null){
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }
}





class ServerHandler implements Runnable{
    private Socket socket;
    public ServerHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression;
            String result;
            while(true){
                //通过BufferedReader读取一行
                //如果已经读到输入流尾部，返回null,退出循环
                //如果得到非空值，就尝试计算结果并返回
                if((expression = in.readLine())==null){
                    break;
                }
                System.out.println("服务器收到消息：" + expression);
                try{
                    //Calculator.cal(expression).toString();
                    result = "123";
                }catch(Exception e){
                    result = "计算错误：" + e.getMessage();
                }
                out.println(result);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //一些必要的清理工作
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(out != null){
                out.close();
                out = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}

class Client {
    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;
    //默认服务器Ip
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String expression){
        send(DEFAULT_SERVER_PORT,expression);
    }
    public static void send(int port,String expression){
        System.out.println("客户端算术表达式为：" + expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            //step 1 创建socket对象
            socket = new Socket(DEFAULT_SERVER_IP,port);
            //step 2 获取此套接字的输入流，并包装为BufferedReader对象
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //step 3 获取此套接字的输出流，并包装为PrintWriter对象。
            out = new PrintWriter(socket.getOutputStream(),true);
            //step 4 往服务端写数据
            out.println(expression);
            //step 5 获取服务端返回的数据
            System.out.println("___结果为：" + in.readLine());

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //step 6 结束后关闭相关的流
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(out != null){
                out.close();
                out = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}



