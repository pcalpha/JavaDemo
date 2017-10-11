package cn.com.pcalpha.nd.netty.discard;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by caiyida on 2016/11/20.
 */
public class ClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //do something msg
            String response = (String) msg;
            System.out.println("Client: " + response);

            ctx.writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));

        } finally {
            //ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
