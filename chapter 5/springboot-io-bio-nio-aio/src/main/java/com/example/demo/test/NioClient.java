package com.example.demo.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
    //管道管理器
    private Selector selector;

    public NioClient init(String serverIp, int port) throws IOException {
        //step1：获取socket通道，并设置为非阻塞方式
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        //step2：获得通道管理器
        selector = Selector.open();

        channel.connect(new InetSocketAddress(serverIp, port));
        //step3：为该通道注册SelectionKey.OP_CONNECT事件
        channel.register(selector, SelectionKey.OP_CONNECT);
        return this;
    }

    public void listen() throws Exception {
        System.out.println("客户端启动");
        //轮询访问selector
        while (true) {
            //step4：选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                //step5：删除已选的key，防止重复处理
                ite.remove();
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    //如果正在连接，则完成连接
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);

                    //连接成功后，注册接收服务器消息的事件
                    channel.register(selector, SelectionKey.OP_READ);
                    //向服务器发送消息
                    channel.write(ByteBuffer.wrap(new String("send message to server").getBytes()));
                //有可读数据事件
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    //创建读取数据的缓存区，大小为10字节
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    channel.read(buffer);
                    String message = new String(buffer.array());

                    System.out.println("recevie message from server, size:" + buffer.position() + " msg: " + message);
                    ByteBuffer outbuffer = ByteBuffer.wrap(("client--->>>".concat(message)).getBytes());
                    channel.write(outbuffer);
                    Thread.sleep(10000);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new NioClient().init("127.0.0.1", 9981).listen();
    }
}