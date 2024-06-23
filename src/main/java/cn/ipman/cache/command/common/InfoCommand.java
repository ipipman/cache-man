package cn.ipman.cache.command.common;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:06
 */
public class InfoCommand implements Command {

    private static final String INFO = "IMCache Server[v1.0.0], created by ipman." + CRLF
            + "Mock Redis Server, at 2024-06-19 in Beijing." + CRLF;

    @Override
    public String name() {
        return "INFO";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        return Reply.bulkString(INFO);
    }
}
