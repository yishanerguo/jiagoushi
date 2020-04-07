//package com.wangshao.netty.runtime;
//
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.util.ReferenceCountUtil;
//
///**
// * @author liutao
// * @create 2020-03-28-14:35
// */
//
//
//public class ClientHandler extends ChannelHandlerAdapter {
//
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        try {
//            Response resp = (Response)msg;
//            System.out.println("Client : " + resp.getId() + ", " + resp.getName() + ", " + resp.getResponseMessage());
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        ctx.close();
//    }
//
//}
