package cn.com.pcalpha.nd.netty.delimiter;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by caiyida on 2016/11/29.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        String request = (String) msg;
        System.out.println("Server:"+request);
        String response="我是响应数据";
        ctx.writeAndFlush(response);

    }
}
