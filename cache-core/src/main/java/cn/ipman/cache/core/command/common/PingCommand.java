package cn.ipman.cache.core.command.common;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:06
 */
public class PingCommand implements Command {

    @Override
    public String name() {
        // PING ===> *1,$4,ping
        return "PING";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String ret = "PONG";
        if (args.length >= 5) {
            ret = getKey(args);
        }
        return Reply.string(ret);
    }
}
