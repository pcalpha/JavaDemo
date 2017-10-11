package cn.com.pcalpha.nd.netty.discard;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * Created by caiyida on 2016/11/20.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //((ByteBuf)msg).release();
//        //do something msg
//        ByteBuf buf = (ByteBuf)msg;
//        byte[] data = new byte[buf.readableBytes()];
//        buf.readBytes(data);
//        String request = new String(data, "utf-8");
//        //System.out.println("Server: " + request);
//        //写给客户端
        String response = "handler1";
        System.out.println("handler1");
        ChannelFuture f = ctx.write(Unpooled.copiedBuffer(response.getBytes()));
        //f.addListener(ChannelFutureListener.CLOSE);
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
