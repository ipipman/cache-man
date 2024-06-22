package cn.ipman.cache.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * M缓存处理程序，负责处理缓存相关的命令请求。
 *
 * @Author IpMan
 * @Date 2024/6/15 14:02
 */
public class IMCacheHandler extends SimpleChannelInboundHandler<String> {

    // 定义回车换行符、OK响应、INFO响应的静态字符串，用于命令响应的构建
    private static final String CRLF = "\r\n";
    private static final String OK = "OK";
    private static final String INFO = "IMCache Server[v1.0.0], created by ipman." + CRLF
            + "Mock Redis Server, at 2024-06-15 in Beijing." + CRLF;

    // 全局缓存实例，用于存储和检索数据。
    public static final IMCache CACHE = new IMCache();


    private static String[] getKeys(String[] args) {
        int len = (args.length - 3) / 2;
        String[] keys = new String[len];
        for (int i = 0; i < len; i++) {
            keys[i] = args[4 + i * 2];
        }
        return keys;
    }

    /**
     * 处理接收到的缓存命令。
     *
     * @param ctx     通道上下文，用于发送响应。
     * @param message 接收到的命令字符串。
     * @throws Exception 如果处理过程中发生异常。
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                String message) throws Exception {

        String[] args = message.split(CRLF);
        System.out.println("IMCacheHandler ==> " + String.join(",", args));

        String cmd = args[2].toUpperCase();

        // 根据redis操作指令,获取具体的执行方法
        Command command = Commands.get(cmd);
        if (command != null) {
            Reply<?> reply = command.exec(CACHE, args);
            System.out.println("CMD[" + cmd + "] => " + reply.type + " => " + reply.value);
            replyContext(ctx, reply);
            return;
        }

         if ("INCR".equals(cmd)) {    // INCR ===> *3,$4,incr,$1,a,$1,1
            String key = args[4];
            try {
                integer(ctx, CACHE.incr(key));
            } catch (NumberFormatException e) {
                error(ctx, "NFE " + key + " value is not integer");
            }
        } else if ("DECR".equals(cmd)) {        // DECR ===>  *2,$4,decr,$1,a
            String key = args[4];
            try {
                integer(ctx, CACHE.decr(key));
            } catch (NumberFormatException e) {
                error(ctx, "NFE " + key + " value is not integer");
            }
        } else { // *2,$7,COMMAND,$4,DOCS
            simpleString(ctx, OK);
        }
    }

    private void replyContext(ChannelHandlerContext ctx, Reply<?> reply) {
        switch (reply.getType()) {
            case INT:
                integer(ctx, (Integer) reply.getValue());
                break;
            case ERROR:
                error(ctx, (String) reply.getValue());
                break;
            case SIMPLE_STRING:
                simpleString(ctx, (String) reply.getValue());
                break;
            case BULK_STRING:
                bulkString(ctx, (String) reply.getValue());
                break;
            case ARRAY:
                array(ctx, (String[]) reply.getValue());
                break;
            default:
                simpleString(ctx, OK);
        }
    }

    // 发送错误响应
    private void error(ChannelHandlerContext ctx, String msg) {
        writeByteBuf(ctx, errorEncode(msg));
    }

    // 发送数组响应
    private void array(ChannelHandlerContext ctx, String[] array) {
        writeByteBuf(ctx, arrayEncode(array));
    }

    // 发送整数响应
    private void integer(ChannelHandlerContext ctx, int i) {
        writeByteBuf(ctx, integerEncode(i));
    }

    // 发送复杂的string响应
    private void bulkString(ChannelHandlerContext ctx, String content) {
        writeByteBuf(ctx, bulkStringEncode(content));
    }

    // 发送简单的string响应
    private void simpleString(ChannelHandlerContext ctx, String content) {
        writeByteBuf(ctx, simpleStringEncode(content));
    }

    // 将数组编码为Redis协议格式的字符串
    private static String arrayEncode(Object[] array) {
        StringBuilder sb = new StringBuilder();
        if (array == null) {
            sb.append("*-1").append(CRLF);
        } else if (array.length == 0) {
            sb.append("*0").append(CRLF);       // 空数组
        } else {
            sb.append("*").append(array.length).append(CRLF);
            for (Object obj : array) {
                if (obj == null) {
                    sb.append("$-1" + CRLF);
                } else {
                    if (obj instanceof Integer) {
                        sb.append(integerEncode((Integer) obj));
                    } else if (obj instanceof String) {
                        sb.append(bulkStringEncode(obj.toString()));
                    } else if (obj instanceof Object[] objs) {
                        sb.append(arrayEncode(objs));
                    }
                }
            }
        }
        return sb.toString();
    }

    // 将整数编码为Redis协议格式的字符串
    private static String integerEncode(int i) {
        return ":" + i + CRLF;
    }

    // 将错误信息编码为Redis协议格式的字符串
    private static String errorEncode(String error) {
        return "-" + error + CRLF;
    }

    // 将复杂的string编码为Redis协议格式的字符串
    private static String bulkStringEncode(String content) {
        String ret;
        if (content == null) {
            ret = "$-1";
        } else if (content.isEmpty()) { // 字符串空
            ret = "$0";
        } else {
            ret = "$" + content.getBytes().length + CRLF + content;
        }
        return ret + CRLF;
    }

    // 将简单的string编码为Redis协议格式的字符串
    private static String simpleStringEncode(String content) {
        String ret;
        if (content == null) {
            ret = "$-1";
        } else if (content.isEmpty()) { // 字符串空
            ret = "$0";
        } else {
            ret = "+" + content;
        }
        return ret + CRLF;
    }

    // 将编码后的字符串写入ByteBuf并发送
    private void writeByteBuf(ChannelHandlerContext ctx, String content) {
        System.out.println("wrap byte buffer and reply: " + content);
        ByteBuf buffer = Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(buffer);
    }

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
// ========================== RedisMessage  ==================================