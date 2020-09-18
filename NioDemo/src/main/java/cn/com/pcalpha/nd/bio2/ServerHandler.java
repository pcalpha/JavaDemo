package cn.com.pcalpha.nd.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by caiyida on 2016/11/19.
 */
public class ServerHandler implements Runnable {
    private Socket socket;
    BufferedReader in = null;
    PrintWriter out = null;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        try {
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String request = in.readLine();
            System.out.println("服务器得到数据:"+request);
            out.print("hello");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                out.close();
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
