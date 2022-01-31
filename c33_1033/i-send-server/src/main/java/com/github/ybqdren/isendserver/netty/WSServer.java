package com.github.ybqdren.isendserver.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

@Slf4j
@Component
public class WSServer {
    private static class SingletionWSServer{
        static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance(){
        return SingletionWSServer.instance;
    }

    private EventLoopGroup  mainGroup;
    private EventLoopGroup  subGroup;
    private ServerBootstrap server;
    private ChannelFuture   future;

    public WSServer(){
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup , subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(null);
    }

    // spring boot 整合时，不需要做同步，如果是在 main 方法中需要进行同步
    public void start(){
        this.future = server.bind(8090);
        log.info("{}" , "netty websocket server 启动完毕 ....");
    }
}
