package cn.com.pcalpha.nd.netty.echo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * Created by caiyida on 2016/11/20.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg); // 把接受到的消息写回
        ctx.flush(); //把缓冲区中数据强行输出
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
