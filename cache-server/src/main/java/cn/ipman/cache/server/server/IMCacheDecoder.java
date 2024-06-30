package cn.ipman.cache.server.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 缓存解码器，用于将字节流解码为可识别的消息对象。
 * 此解码器负责从ByteBuf中读取数据，并将其转换为字符串形式添加到输出列表中。
 *
 * @Author IpMan
 * @Date 2024/6/15 14:40
 */
public class IMCacheDecoder extends ByteToMessageDecoder {

    AtomicLong counter = new AtomicLong();

    /**
     * 解码方法，Netty框架调用此方法进行实际的解码操作。
     *
     * @param channelHandlerContext Netty的通道处理上下文，用于通道的管理和操作。
     * @param in                    输入的ByteBuf，包含待解码的数据。
     * @param out                   输出列表，解码后的消息对象将被添加到此列表中。
     * @throws Exception 如果解码过程中发生错误。
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf in, List<Object> out) throws Exception {
        System.out.println("IMCacheDecoder decodeCount:" + counter.incrementAndGet());

        // 获取当前可读字节的长度和读取索引
        if (in.readableBytes() <= 0) return;
        int count = in.readableBytes();
        int index = in.readerIndex();
        System.out.println("IMCacheDecoder count:" + count + ", index:" + index);

        // 根据可读字节长度创建字节数组，并从ByteBuf中读取字节到字节数组
        byte[] bytes = new byte[count];
        in.readBytes(bytes);

        // 将字节数组转换为字符串
        String ret = new String(bytes);
        System.out.println("IMCacheDecoder ret:" + ret);

        // 将解码后的字符串添加到输出列表中
        out.add(ret);
    }
}
