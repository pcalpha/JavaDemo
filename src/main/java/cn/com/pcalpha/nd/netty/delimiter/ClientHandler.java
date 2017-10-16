package cn.com.pcalpha.nd.netty.delimiter;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by caiyida on 2016/11/29.
 */
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        String response = (String) msg;
        System.out.println(response);
        ReferenceCountUtil.release(msg);
    }
}
