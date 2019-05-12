package com.example.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

/**
 * 描述：服务端
 * @author ay
 * @date 2019-05-11
 */
public class NettyServer {


    private static final int port = 8080;

    public static void main(String[] args) {
        try {
            //启动服务端
            new NettyServer().start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() throws InterruptedException {
        //Bootstrap主要作用是配置整个Netty程序，串联各个组件
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //通过nio方式来接收连接和处理连接
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            serverBootstrap.group(group);
            // 设置nio类型的channel
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 设置监听端口
            serverBootstrap.localAddress(new InetSocketAddress(port));
            //连接到达时会创建一个channel
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel){
                    // pipeline管理channel中的Handler，在channel队列中添加一个handler来处理业务
                    socketChannel.pipeline().addLast("myHandler", new NettyServerHandler());
                }
            });
            // 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("Server started and listen on " + channelFuture.channel().localAddress());
            // 应用程序会一直等待，直到channel关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            group.shutdownGracefully().sync();
        }
    }
}
