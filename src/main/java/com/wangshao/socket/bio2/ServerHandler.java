package com.wangshao.socket.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author liutao
 * @create 2020-03-26-15:30
 */


public class ServerHandler implements Runnable{

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out =null;
        try {
             in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             out = new PrintWriter(this.socket.getOutputStream(), true);
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("server:" + body);
                out.println("server response");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
           // socket = null;
        }

    }
}
