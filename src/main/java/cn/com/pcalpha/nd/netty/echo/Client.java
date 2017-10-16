package cn.com.pcalpha.nd.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by caiyida on 2016/11/20.
 */
public class Client implements Runnable {
    public static void main(String[] args) {
        new Thread(new Client()).run();
    }
    public void run() {


        EventLoopGroup workgroup = new NioEventLoopGroup();



        try {
            Bootstrap b = new Bootstrap();
            b.group(workgroup)
                    /***
                     * ServerSocketChannel以NIO的selector为基础进行实现的，用来接收新的连接
                     * 这里告诉Channel如何获取新的连接.
                     */
                    .channel(NioSocketChannel.class)
                    /***
                     * 这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。
                     * ChannelInitializer是一个特殊的处理类，
                     * 他的目的是帮助使用者配置一个新的Channel。
                     * 也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel
                     * 或者其对应的ChannelPipeline来实现你的网络程序。
                     * 当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，
                     * 然后提取这些匿名类到最顶层的类上。
                     */
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new FixedLengthFrameDecoder(9));//消息定长
                            sc.pipeline().addLast(new StringDecoder());
                            sc.pipeline().addLast(new ClientHandler());

                        }
                    });
            ChannelFuture cf1 = null;
            cf1 = b.connect("127.0.0.1", 8765).sync();

            //buf
            cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
            cf1.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
            cf1.channel().writeAndFlush(Unpooled.copiedBuffer("999".getBytes()));

            cf1.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workgroup.shutdownGracefully();

        }



    }

}
