package com.wangshao.socket.bio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liutao
 * @create 2020-03-26-14:31
 */


public class Server {

    final static int PORT = 8765;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            System.out.println("server start ..");
            Socket socket = null;
            HandlerExecutorPool executorPool = new HandlerExecutorPool(50,1000 );
            while (true) {
                socket = server.accept();
                executorPool.execute(new ServerHandler(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if(server != null){
                try {
                    server.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            //server = null;
        }
    }
}
