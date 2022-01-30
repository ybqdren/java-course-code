package com.github.ybqdren;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 创建自定义助手类 </h1>
 * <p>
 *     {@link SimpleChannelInboundHandler} 对于请求来讲，其实相当于 [入站，入境]
 *
 * </p>
 **/
public class CustomeHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel ... 注册");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel ... 移除");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel ... 活跃中");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel ... 不活跃");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel ... 读取完毕");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("Channel ... 用户事件触发");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel ... 可写更改");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Channel ... 捕获到异常");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel ... 助手类增加");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel ... 助手类移除");
        super.handlerRemoved(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 从上下文中获取 channel
        Channel channel = ctx.channel();

        if(msg instanceof HttpRequest){
            // 显示客户端的远程地址
            System.out.println(channel.remoteAddress());

            // 深拷贝 buffer
            // 定义发送的数据消息
            ByteBuf content = Unpooled.copiedBuffer("Hello netty ~" , CharsetUtil.UTF_8);

            // 构建一个 http.response
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1 ,
                    HttpResponseStatus.OK,
                    content);

            // 为相应增加数据类型和长度
            // 设置数据类型
            response.headers().set(HttpHeaderNames.CONTENT_TYPE , "text/plain");

            // 设置数据长度
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH , content.readableBytes());

            // 把相应发送到客户端
//        channel.write() 只写入缓冲区，而不会发送到客户端
            channel.writeAndFlush(response);   // 写 并刷新到客户端
        }

    }
}
