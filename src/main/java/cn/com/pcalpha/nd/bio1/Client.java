package cn.com.pcalpha.nd.bio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by caiyida on 2016/11/19.
 */
public class Client {
    private final static String ADDRESS = "127.0.0.1";
    private final static int PORT = 8765;

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket(ADDRESS,PORT);

            out = new PrintWriter(socket.getOutputStream(),true);
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("客户端发送数据");

            String response = in.readLine();
            System.out.println("客户端收到数据:"+response);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
