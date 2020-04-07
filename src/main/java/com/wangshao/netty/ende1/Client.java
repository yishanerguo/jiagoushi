//package com.wangshao.netty.ende1;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.codec.string.StringDecoder;
//
//
///**
// * @author liutao
// * @create 2020-03-27-15:20
// */
//
//
//public class Client {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        EventLoopGroup group = new NioEventLoopGroup();
//
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(group)
//                .channel(NioSocketChannel.class)
//                .handler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
//                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new ClientHandler());
//                    }
//                });
//
//        ChannelFuture cf = bootstrap.connect("127.0.0.1", 8765).sync();
//        cf.channel().writeAndFlush(Unpooled.wrappedBuffer("bbbb$_".getBytes()));
//        cf.channel().writeAndFlush(Unpooled.wrappedBuffer("cccc$_".getBytes()));
//
//        //等待客户端端口关闭
//        cf.channel().closeFuture().sync();
//        group.shutdownGracefully();
//    }
//}
