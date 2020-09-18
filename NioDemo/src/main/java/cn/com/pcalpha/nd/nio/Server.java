package cn.com.pcalpha.nd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by caiyida on 2016/11/19.
 */
public class Server implements Runnable {
    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    public Server(int port) {

        try {
            //1.打开复用器,单selector
            this.selector = Selector.open();
            //2.打开复用器通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //3.设置服务器通道为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //4.绑定地址

            serverSocketChannel.bind(new InetSocketAddress(port));
            //5.把服务器通道注册到复用器上,并监听阻塞事件
            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("DiscardServer start,port"+port);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void run() {
        while(true){
            try {
                //1.让多路复用器进行监听,阻塞
                this.selector.select();
                //2.返回多路复用器已经选择的结果集
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                //3.进行遍历
                while(keys.hasNext()){
                    //4.获取一个选择的元素
                    SelectionKey key = keys.next();
                    //5.直接从容器中移除
                    keys.remove();
                    //6.如果有效
                    if (key.isValid()){
                        //7.是否阻塞状态
                        if(key.isAcceptable()){
                            this.accept(key);
                        }
                        //8.如果为可读状态
                        if(key.isReadable()){
                            this.read(key);
                        }
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void accept(SelectionKey key){

        try {
            //1.获取服务器通道
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            //2.执行阻塞方法
            SocketChannel channel = serverSocketChannel.accept();
            //3.设置阻塞状态
            channel.configureBlocking(false);
            //4.注册到多路复用器上,并设置读取标志
            channel.register(this.selector,SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key){

        try {
            //1.清空缓冲区
            this.readBuffer.clear();
            //2.获取注册的socket对象
            SocketChannel channel = (SocketChannel) key.channel();
            //3.读取数据
            int count = 0;
            count = channel.read(this.readBuffer);
            //4.如果没有数据
            if(count== -1){
                key.channel().close();
                key.cancel();
                return;

            }
            //5.切换到读取模式
            this.readBuffer.flip();
            //6.根据缓冲区大小创建字节数组
            byte[] bytes = new byte[this.readBuffer.remaining()];
            //7.接受缓冲区数据
            this.readBuffer.get(bytes);
            //8.打印结果
            String body = new String(bytes).trim();
            System.out.println("DiscardServer:"+body);

            //TODO 回写客户端

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void main(String[] args) {
        new Thread(new Server(8765)).start();
    }
}
