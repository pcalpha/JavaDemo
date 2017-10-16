package cn.com.pcalpha.nd.netty.heart;

import cn.com.pcalpha.nd.netty.serialize.MarshallingCodeCFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by caiyida on 2016/11/20.
 */
public class Server {


    public static void main(String[] args) {
        //1 第一个线程组 是用于接收Client端连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2 第二个线程组 是用于实际的业务处理操作的
        EventLoopGroup worketGroup = new NioEventLoopGroup();


        try {
            //3 创建一个辅助类Bootstrap，就是对我们的Server进行一系列的配置s
            ServerBootstrap bootstrap = new ServerBootstrap();
            //把俩个工作线程组加入进来
            bootstrap.group(bossGroup,worketGroup)
                    //我要指定使用NioServerSocketChannel这种类型的通道
                    .channel(NioServerSocketChannel.class)
                    //一定要使用 childHandler 去绑定具体的 事件处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            socketChannel.pipeline().addLast(new ServerHeartBeatHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .option(ChannelOption.SO_SNDBUF,32*1024)
                    .option(ChannelOption.SO_RCVBUF,32*1024)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            //绑定指定的端口 进行监听
            ChannelFuture f = bootstrap.bind(8765).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            worketGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
