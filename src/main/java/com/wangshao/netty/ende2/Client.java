//package com.wangshao.netty.ende2;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.FixedLengthFrameDecoder;
//import io.netty.handler.codec.string.StringDecoder;
//
///**
// * @author liutao
// * @create 2020-03-27-15:48
// */
//
//
//public class Client {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        EventLoopGroup group = new NioEventLoopGroup();
//
//        Bootstrap b = new Bootstrap();
//        b.group(group)
//                .channel(NioSocketChannel.class)
//                .handler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new FixedLengthFrameDecoder(5));
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new ClientHandler());
//                    }
//                });
//        ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();
//
//        cf.channel().writeAndFlush(Unpooled.wrappedBuffer("aaaaabbbbb".getBytes()));
//        cf.channel().writeAndFlush(Unpooled.copiedBuffer("ccc  ".getBytes()));
//
//        //等待客户端端口关闭
//        cf.channel().closeFuture().sync();
//        group.shutdownGracefully();
//
//    }
//}
