package cn.ipman.cache;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.redis.ArrayHeaderRedisMessage;
import io.netty.handler.codec.redis.BulkStringHeaderRedisMessage;
import io.netty.handler.codec.redis.DefaultBulkStringRedisContent;
import io.netty.handler.codec.redis.RedisMessage;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/15 14:02
 */
public class IMCacheHandler extends SimpleChannelInboundHandler<RedisMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                RedisMessage message) throws Exception {
        if (message instanceof ArrayHeaderRedisMessage msg) {
            System.out.println("1 ==> " + msg.length());
        }

        if (message instanceof BulkStringHeaderRedisMessage msg) {
            System.out.println("2 ==> " + msg.bulkStringLength());
        }

        if (message instanceof DefaultBulkStringRedisContent msg) {
            int count = msg.content().readableBytes();
            byte[] bytes = new byte[count];
            msg.content().readBytes(bytes);
            System.out.println("3 ==> " + new String(bytes));
            channelHandlerContext.writeAndFlush("+OK\r\n");
        }

    }
}
