package cn.com.pcalpha.nd.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created by caiyida on 2016/11/20.
 */
public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {
    //
    @Override
    public void completed(AsynchronousSocketChannel result, Server attachment) {
        //当有一个客户端接入的时候,调用Server的accept方法,这样反复进行
        attachment.getAssc().accept(attachment,this);
        read(result);
    }

    private void read(final AsynchronousSocketChannel asc) {
        //读取数据
        ByteBuffer buf = ByteBuffer.allocate(1024);
        asc.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                //读取之后重置标识位
                attachment.flip();
                //设置读取的字节数
                System.out.println("DiscardServer->"+"收到的数据长度"+result);
                String resultData = new String(attachment.array()).trim();
                System.out.println("DiscardServer->"+"收到的信息为"+resultData);
                String response ="服务器响应,收到的客户端发来的数据"+resultData;
                write(asc,response);
            }



            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });

    }

    private void write(AsynchronousSocketChannel asc, String response) {

        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(response.getBytes());
            byteBuffer.flip();
            asc.write(byteBuffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }
    
    
}