package cn.ipman.cache.core.command.common;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 14:16
 */
public class ExpireCommand implements Command {

    @Override
    public String name() {
        // *2,$7,COMMAND,$4,DOCS
        return "EXPIRE";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        long ttl = Long.parseLong(getValue(args));
        return Reply.integer(cache.expire(key, ttl));
    }
}
