package cn.com.pcalpha.nd.netty.serialize;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by caiyida on 2016/11/29.
 */
public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        String request = (String) msg;
        System.out.println("Server:"+request);
        String response="我是响应数据";
        ctx.writeAndFlush(response);
    }
}
