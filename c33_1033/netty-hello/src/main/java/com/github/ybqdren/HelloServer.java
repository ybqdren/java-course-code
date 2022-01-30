package com.github.ybqdren;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> 实现客户端发送一个请求，服务器会返回 hello world </h1>
 * <p> 使用主从线程模型 </p>
 **/
public class HelloServer {
    public static void main(String[] args) {
        // 定义一对线程组

        // 定义一个线程组：主线程组：用于接受客户端的连接，但是不做任何处理，根老板一样，不做事
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // 从线程组：老板线程组会把任务丢给他，让手下线程去做任务
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // netty 服务器的创建，ServerBootstrap 是一个启动类
        serverBootstrap.group(bossGroup , workGroup)           // 设置主从线程组
                        .channel(NioServerSocketChannel.class) // 设置 nio 的双向通道
                        .childHandler(new HelloServerInitializer()); // 子处理器（可自实现），用于处理 workerGroup

        try {
            // 启动服务器,设置端口号他，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088) // 绑定端口
                    .sync();   // 设置同步的等待
            // 监听关闭的 channel ，设置位同步方式
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
          bossGroup.shutdownGracefully();
          workGroup.shutdownGracefully();
        }
    }
}
