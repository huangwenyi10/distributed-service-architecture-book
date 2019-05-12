package com.example.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 描述：客户端业务处理类
 * @author ay
 * @date 2019-05-11
 */
public class NettyClientHandler  extends ChannelInboundHandlerAdapter {

    /**
     *m描述：此方法会在连接到服务器后被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String msg = "hello Server!";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
    /**
     *描述:此方法会在接收到服务器数据后调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf result = (ByteBuf) msg;
        byte[] content = new byte[result.readableBytes()];
        result.readBytes(content);
        System.out.println("Server said:" + new String(content));
        result.release();
    }

    /**
     *描述：捕捉到异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
