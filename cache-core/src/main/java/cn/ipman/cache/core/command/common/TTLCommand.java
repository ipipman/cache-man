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
public class TTLCommand implements Command {

    @Override
    public String name() {
        // ttl ===>  *2,$3,ttl,$1,a
        return "TTL";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.ttl(key));
    }
}
