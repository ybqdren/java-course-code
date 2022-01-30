package com.github.ybqdren.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhao wen
 * @since 1.0.0
 **/
public class WSServer {
    public static void main(String[] args) {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        server.group(mainGroup , subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());

        try {
            ChannelFuture channel = server.bind(8089).sync();
            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }
}
