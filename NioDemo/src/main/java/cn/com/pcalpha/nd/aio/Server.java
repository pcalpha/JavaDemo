package cn.com.pcalpha.nd.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caiyida on 2016/11/20.
 */
public class Server {

    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup threadGroup;
    //服务器通道
    private AsynchronousServerSocketChannel assc;

    public Server(int port) {


        try {
            //创建一个缓冲区
            executorService = Executors.newCachedThreadPool();
            //创建线程组
            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
            //创建服务器通道
            assc = AsynchronousServerSocketChannel.open(threadGroup);
            //进行绑定
            assc.bind(new InetSocketAddress(port));

            System.out.println("DiscardServer start,port:"+port);
            //进行阻塞
            assc.accept(this,new ServerCompletionHandler());

            Thread.sleep(Integer.MAX_VALUE);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Server server = new Server(8765);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public AsynchronousChannelGroup getThreadGroup() {
        return threadGroup;
    }

    public void setThreadGroup(AsynchronousChannelGroup threadGroup) {
        this.threadGroup = threadGroup;
    }

    public AsynchronousServerSocketChannel getAssc() {
        return assc;
    }

    public void setAssc(AsynchronousServerSocketChannel assc) {
        this.assc = assc;
    }
}
