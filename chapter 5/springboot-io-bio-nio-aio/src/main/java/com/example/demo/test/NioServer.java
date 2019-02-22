package com.example.demo.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

class NioServer {

    /** 通道管理器 **/
    private Selector selector;

    public NioServer init(int port) throws IOException {
        //step1: 获取ServerSocket通道,设置为非阻塞方式,绑定端口
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        //step2：获取通道管理器
        selector = Selector.open();
        //step3：将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，
        //只有当该事件到达时，Selector.select()才会返回，否则一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        return this;
    }

    public void listen() throws Exception {
        System.out.println("服务器端启动成功...");
        //使用轮询访问selector
        while (true) {
            //step4：当有注册的事件到达时，方法返回，否则阻塞。
            selector.select();
            //获取所有注册事件
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            //循环判断所有注册的时间是否就绪
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                //删除已选key，防止重复处理
                ite.remove();
                //客户端请求连接事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    //获得客户端连接通道
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    //在与客户端连接成功后，为客户端通道注册SelectionKey.OP_READ事件。
                    channel.register(selector, SelectionKey.OP_READ);
                    //向客户端发消息
                    channel.write(ByteBuffer.wrap(new String("send message to client").getBytes()));
                //有可读数据事件
                } else if (key.isReadable()) {
                    //获取客户端传输数据可读取消息通道。
                    SocketChannel channel = (SocketChannel) key.channel();
                    //创建读取数据缓冲器
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    int read = channel.read(buffer);
                    byte[] data = buffer.array();
                    String message = new String(data);
                    System.out.println("receive message from client, size:" + buffer.position() + " msg: " + message);
                    ByteBuffer outbuffer = ByteBuffer.wrap(("server --->>>".concat(message)).getBytes());
                    channel.write(outbuffer);

                    Thread.sleep(10000);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new NioServer().init(9981).listen();
    }
}