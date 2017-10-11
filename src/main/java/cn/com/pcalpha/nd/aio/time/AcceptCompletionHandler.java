package cn.com.pcalpha.nd.aio.time;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements
        CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result,
                          AsyncTimeServerHandler attachment) {
        /**
         * 可能读者在此可能会心存疑惑，既然已经接收客户端成功了，为什么还要再次调用accept方法呢？
         * 原因是这样的：当我们调用AsynchronousServerSocketChannel的accept方法后，
         * 如果有新的客户端连接接入，系统将回调我们传入的CompletionHandler实例的completed方法，
         * 表示新的客户端已经接入成功，因为一个AsynchronousServerSocketChannel可以接收成千上万个客户端，
         * 所以我们需要继续调用它的accept方法，接收其它的客户端连接，最终形成一个循环。
         */
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
