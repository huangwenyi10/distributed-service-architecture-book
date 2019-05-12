package com.example.demo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 描述：NettyServerHandler类是具体的业务类，
 * @author ay
 * @date 2019-05-11
 */

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 描述:读取客户端发送的消息
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf result = (ByteBuf) msg;
        byte[] content = new byte[result.readableBytes()];
        //msg中存储的是ByteBuf类型的数据,把数据读取到byte[]
        result.readBytes(content);
        //接收并打印客户端的信息
        System.out.println("Client said:" + new String(content));
        //释放资源，这行很关键
        result.release();
        //向客户端发送消息
        String response = "hello client!";
        //在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }

    /**
     * 描述:信息获取完毕后操作
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //flush掉所有写回的数据
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                //当flush完成后关闭channel
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 描述:用于处理异常
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //捕捉异常信息
        cause.printStackTrace();
        //出现异常时关闭channel
        ctx.close();
    }

}

