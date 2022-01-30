package com.github.ybqdren.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 处理消息的 handler </h1>
 *
 * <p>
 *     {@link TextWebSocketFrame} 在 websocket 中传递 text
 *     此类是在 netty 中，专门为 websocket 专门处理文本的对象， frame 是消息的载体
 * </p>
 **/
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /** 记录和管理所有客户端的 channel，使用 GlobalEventExecutor.INSTANCE 可以为所有 channel 进行初始化 */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取客户端传输过来的消息
        String content = msg.text();
        System.out.println("接收到的数据：" + content);

        for(Channel channel : clients){
            // 将消息写入缓冲区，并刷新到客户端中
            channel.writeAndFlush(new TextWebSocketFrame("[服务器接收到消息：]" + LocalDateTime.now())
                                                              + "接收到消息 ， 消息为："  + content);
        }

        // 下面这个方法和下面这个 for 循环一致
        clients.writeAndFlush(
                new TextWebSocketFrame(
                        "[服务器接收到消息：]" + LocalDateTime.now())
                        + "接收到消息 ， 消息为："  + content);
    }

    // 将消息转发给客户端，每个客户端对应着每个 channel
    /**
     * <h2> 将客户端的 channel 放到 channelGroup 中进行管理 </h2>
     * <p> 当客户端连接服务端之后，即 channel 创建完成后就会触发 </p>
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    /** channel 离开后会触发 */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发 handlerRemoved，ChannelGroup 会自动移除对应客户端的 channel
//        clients.remove(ctx.channel());
        System.out.println("客户端断开 ， channel 对应的长 id 为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开 ， channel 对应的短 id 为：" + ctx.channel().id().asShortText());
    }
}
