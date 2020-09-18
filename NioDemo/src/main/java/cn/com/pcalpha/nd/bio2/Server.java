package cn.com.pcalpha.nd.bio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by caiyida on 2016/11/19.
 */
public class Server {
    private final static int PORT = 8765;
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            System.out.println("server start");

            HandlerExecutorPool threadPool = new HandlerExecutorPool(50,1000);
            while(true){
                Socket socket = server.accept();
                threadPool.execute(new ServerHandler(socket));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            server = null;
        }

    }
}
