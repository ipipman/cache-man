package cn.ipman.cache.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Cache Server
 *
 * @Author IpMan
 * @Date 2024/6/15 13:37
 */
@Component
public class IMCacheServer implements IMServerPlugin {

    int port = 6379;
    EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    Channel channel;

    @Override
    public void init() {
        bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("redis-boss"));
        workerGroup = new NioEventLoopGroup(16, new DefaultThreadFactory("redis-work"));
    }

    @Async
    @Override
    public void startup() {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128)                     // 连接队列大小
                    .childOption(ChannelOption.TCP_NODELAY, true)       // 关闭Nagle,即时传输
                    .childOption(ChannelOption.SO_KEEPALIVE, true)      // 支持长连接
                    .childOption(ChannelOption.SO_REUSEADDR, true)      // 共享端口
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)    // 操作缓冲区的大小
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)    // 发送缓冲区的大小
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new IMCacheDecoder());
                            ch.pipeline().addLast(new IMCacheHandler());
                        }
                    });

            channel = b.bind(port).sync().channel();
            System.out.println("开启netty redis服务器，端口为 " + port);
            channel.closeFuture().sync();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            shutdown();
        }
    }

    @Override
    public void shutdown() {
        if (this.channel != null) {
            this.channel.close();
            this.channel = null;
        }
        if (this.bossGroup != null) {
            this.bossGroup.shutdownGracefully();
            this.bossGroup = null;
        }
        if (this.workerGroup != null) {
            this.workerGroup.shutdownGracefully();
            this.workerGroup = null;
        }
    }
}
