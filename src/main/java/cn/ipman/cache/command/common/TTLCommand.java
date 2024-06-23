package cn.ipman.cache.command.common;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 14:16
 */
public class TTLCommand implements Command {

    @Override
    public String name() {
        // *2,$7,COMMAND,$4,DOCS
        return "TTL";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.ttl(key));
    }
}
