package com.wangshao.socket.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * @author liutao
 * @create 2020-03-26-21:01
 */


public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,Server> {


    @Override
    public void completed(AsynchronousSocketChannel asc, Server attachment) {
        //当有下一个客户端接入的时候,直接调用server的accept方法,这样反复执行下去,保证多个客户端都可以阻塞
        attachment.assc.accept(attachment, this);
        read(asc);
    }

    private void read(final AsynchronousSocketChannel asc ){
        //读取数据
        ByteBuffer buf = ByteBuffer.allocate(1024);
        asc.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                //进行读取之后,重置标示位
                attachment.flip();
                //获取读取的字节数
                System.out.println("server - >" + "收到客户端的数据长度为:" + result);
                //获取读取的数据
                String resultData = new String(attachment.array()).trim();
                System.out.println("server -> " + "收到客户端的数据信息为:" + resultData);
                String response =  " 服务器响应, 收到了客户端发来的数据:" + resultData;
                write(asc,response);
            }



            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }

    private void write(AsynchronousSocketChannel asc, String response) {
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.put(response.getBytes());
            buf.flip();
            asc.write(buf).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
