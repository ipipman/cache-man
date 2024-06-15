package cn.ipman.cache;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/15 14:40
 */
public class IMCacheDecoder extends ByteToMessageDecoder {

    AtomicLong counter = new AtomicLong();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf in, List<Object> out) throws Exception {
        System.out.println("IMCacheDecoder decodeCount:" + counter.incrementAndGet());

        if (in.readableBytes() <= 0) return;
        int count = in.readableBytes();
        int index = in.readerIndex();
        System.out.println("IMCacheDecoder count:" + count + ", index:" + index);

        byte[] bytes = new byte[count];
        in.readBytes(bytes);
        String ret = new String(bytes);
        System.out.println("IMCacheDecoder ret:" + ret);

        out.add(ret);
    }
}
