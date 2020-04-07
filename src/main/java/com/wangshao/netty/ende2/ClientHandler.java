//package com.wangshao.netty.ende2;
//
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//
///**
// * @author liutao
// * @create 2020-03-27-15:46
// */
//
//
//public class ClientHandler extends ChannelHandlerAdapter {
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("client channel active... ");
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        String response = (String)msg;
//        System.out.println("client:" + response);
//    }
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//    }
//
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//    }
//}
