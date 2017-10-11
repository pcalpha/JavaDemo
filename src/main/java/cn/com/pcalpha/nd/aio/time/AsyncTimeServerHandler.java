package cn.com.pcalpha.nd.aio.time;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncTimeServerHandler {

    private int port;

    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            //创建线程组
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel
                    .open(threadGroup);
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            asynchronousServerSocketChannel.accept(this,
                    new AcceptCompletionHandler());
            System.out.println("The time server is start in port : " + port);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    /*
//     * (non-Javadoc)
//     *
//     * @see java.lang.Runnable#run()
//     */
//    @Override
//    public void run() {
//
//        //latch = new CountDownLatch(1);
//        doAccept();
////        try {
////            latch.await();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }
//
//    public void doAccept() {
//
//    }
}