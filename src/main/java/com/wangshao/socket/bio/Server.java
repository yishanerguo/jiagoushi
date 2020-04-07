package com.wangshao.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liutao
 * @create 2020-03-26-13:40
 */


public class Server {

    final  static  int port = 8764;

    public static void main(String[] args) {

        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("server start ...");

            //进行阻塞
             Socket socket = server.accept();
             //新建一个线程执行客户端任务
            new Thread(new ServerHandler(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (server != null) {
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
