//package com.wangshao.netty.ende1;
//
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//
///**
// * @author liutao
// * @create 2020-03-27-15:14
// */
//
//
//public class ServerHandler extends ChannelHandlerAdapter {
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(" server channel active... ");
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//       String request = (String) msg;
//       System.out.println("server:" + msg);
//        String response = "服务器响应:" + msg + "$_";
//        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
//    }
//
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) throws Exception {
//        ctx.close();
//    }
//}
