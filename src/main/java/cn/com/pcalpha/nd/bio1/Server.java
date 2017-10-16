package cn.com.pcalpha.nd.bio1;

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
            Socket socket = server.accept();
            new Thread(new ServerHandler(socket)).start();


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
