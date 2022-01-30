package com.github.ybqdren;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 初始化器 </h1>
 * <p> channel 注册后，会执行里面的相应的初始化方法 </p>
 **/
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 通过 SocketChannel 去获得对应的管道
        ChannelPipeline pipeline = channel.pipeline();

        // 通过管道，添加 handler
        // HttpServerCodec 是由 netty 自己提供的助手类，可以理解为拦截器
        // 当请求到服务端，我们需要做解码，相应到客户端做编码
        pipeline.addLast("HttpServerCodec" , new HttpServerCodec());

        // 自定义的 handler ，返回 "Hello Netty"
        pipeline.addLast("customHandler" , null);
    }
}
