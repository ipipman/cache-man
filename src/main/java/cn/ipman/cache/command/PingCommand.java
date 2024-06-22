package cn.ipman.cache.command;

import cn.ipman.cache.core.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

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
        return Reply.string("PONG");
    }
}
