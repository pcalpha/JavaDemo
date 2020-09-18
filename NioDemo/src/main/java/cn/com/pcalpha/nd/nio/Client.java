package cn.com.pcalpha.nd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by caiyida on 2016/11/19.
 */
public class Client {
    public static void main(String[] args) {
        //创建连接地址
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8765);
        //声明连接通道
        SocketChannel socketChannel = null;
        //建立缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


        try {
            //打开通道
            socketChannel = SocketChannel.open();
            //连接服务器
            socketChannel.connect(address);

            while(true){
                //定义字节数组
                byte[] bytes = new byte[1024];
                System.in.read(bytes);
                //读取数据到缓冲区
                byteBuffer.put(bytes);
                //缓冲区复位
                byteBuffer.flip();
                //写出数据
                socketChannel.write(byteBuffer);
                //清空缓冲区数据
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socketChannel!=null){
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
