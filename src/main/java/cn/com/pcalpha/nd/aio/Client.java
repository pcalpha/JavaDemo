package cn.com.pcalpha.nd.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * Created by caiyida on 2016/11/20.
 */
public class Client implements Runnable {
    private AsynchronousSocketChannel asc;
    @Override
    public void run() {

    }

    public Client() {
        try {
            asc = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        this.asc.connect(new InetSocketAddress("127.0.0.1",8765));
    }

    public void write(String request){
        try {
            asc.write(ByteBuffer.wrap(request.getBytes())).get();
            read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void read() {

        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            asc.read(byteBuffer).get();
            byteBuffer.flip();
            byte[] respByte = new byte[byteBuffer.remaining()];
            byteBuffer.get(respByte);
            System.out.println(new String(respByte,"UTF-8").trim());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
