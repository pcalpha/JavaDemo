package cn.com.pcalpha.nd.netty.serialize;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by caiyida on 2016/11/29.
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup workgroup = new NioEventLoopGroup();



        try {
            Bootstrap b = new Bootstrap();
            b.group(workgroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            sc.pipeline().addLast(new ClientHandler());

                        }
                    });
            ChannelFuture cf1 = null;
            cf1 = b.connect("127.0.0.1", 8765).sync();

            //buf
            cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777$_".getBytes()));
            cf1.channel().writeAndFlush(Unpooled.copiedBuffer("888$_".getBytes()));
            cf1.channel().writeAndFlush(Unpooled.copiedBuffer("999$_9999999$_".getBytes()));

            cf1.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workgroup.shutdownGracefully();
        }
    }
}
