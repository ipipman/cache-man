package cn.ipman.cache;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/15 14:02
 */
public class IMCacheHandler extends SimpleChannelInboundHandler<String> {

    private static final String CRLF = "\r\n";
    private static final String STR_PREFIX = "+";
    private static final String OK = "OK";
    private static final String INFO = "IMCache Server[v1.0.0], created by ipman." + CRLF
            + "Mock Redis Server, at 2024-06-15 in Beijing." + CRLF;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                String message) throws Exception {

        String[] args = message.split(CRLF);
        System.out.println("IMCacheHandler ==> " + String.join(",", args));
        // IMCacheHandler ==> *2,$7,COMMAND,$4,DOCS
        String cmd = args[2].toUpperCase();

        if ("PING".equals(cmd)) {
            String ret = "PONG";
            if (args.length >= 5) {
                ret = args[4];
            }
            sampleString(ctx, ret);
        } else if ("INFO".equals(cmd)) {
            bulkString(ctx, INFO);
        } else {
            sampleString(ctx, OK);
        }
    }

    private void bulkString(ChannelHandlerContext ctx, String content) {
        writeByteBuf(ctx, "$" + content.getBytes().length + CRLF + content + CRLF);
    }

    private void sampleString(ChannelHandlerContext ctx, String content) {
        writeByteBuf(ctx, STR_PREFIX + content + CRLF);
    }

    private void writeByteBuf(ChannelHandlerContext ctx, String content) {
        System.out.println("wrap byte buffer and reply: " + content);
        //ByteBuf buffer = Unpooled.buffer(128);
        // buffer.writeBytes(content.getBytes());
        ByteBuf buffer = Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(buffer);
    }


// ========================== RedisMessage  ==================================
//    @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
//                                RedisMessage message) throws Exception {
//        if (message instanceof ArrayHeaderRedisMessage msg) {
//            System.out.println("1 ==> " + msg.length());
//        }
//
//        if (message instanceof BulkStringHeaderRedisMessage msg) {
//            System.out.println("2 ==> " + msg.bulkStringLength());
//        }
//
//        if (message instanceof DefaultBulkStringRedisContent msg) {
//            int count = msg.content().readableBytes();
//            byte[] bytes = new byte[count];
//            msg.content().readBytes(bytes);
//            System.out.println("3 ==> " + new String(bytes));
//            channelHandlerContext.writeAndFlush("+OK\r\n");
//        }
//
//    }
}
