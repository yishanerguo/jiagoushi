//package com.wangshao.netty.helloworld;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//
///**
// * @author liutao
// * @create 2020-03-27-2:11
// */
//
//
//public class ServerHandler extends ChannelHandlerAdapter {
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("server channel active .....");
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println(buf.readableBytes());
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "utf-8");
//        System.out.println("server:" + body);
//        String response = " 进行返回给客户端的响应:" + body;
//        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
//        //addListener(ChannelFutureListener.CLOSE);
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("读完了");
//        //ChannelHandlerContext关闭后,在提交数据不会接收
//       ctx.flush();
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        ctx.close();
//    }
//}
