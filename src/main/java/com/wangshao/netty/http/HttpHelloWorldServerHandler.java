//package com.wangshao.netty.http;
//
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.http.*;
//
//import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
//import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
//import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
//
//
///**
// * @author liutao
// * @create 2020-03-28-21:22
// */
//
//
//public class HttpHelloWorldServerHandler  extends ChannelHandlerAdapter {
//
//    private static final byte[] CONTENT = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) {
//        ctx.flush();
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        if (msg instanceof HttpRequest) {
//            HttpRequest req = (HttpRequest) msg;
//
//            if (HttpHeaderUtil.is100ContinueExpected(req)) {
//                ctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
//            }
//
//            boolean keepAlive = HttpHeaderUtil.isKeepAlive(req);
//            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(CONTENT));
//            response.headers().set(CONTENT_TYPE, "text/plain");
//            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
//
//            if (!keepAlive) {
//                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//            } else {
//                response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//                ctx.write(response);
//            }
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }
//
//}
