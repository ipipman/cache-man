package cn.ipman.cache.core.command.hash;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class HdelCommand implements Command {

    @Override
    public String name() {
        // hdel ===>  *3,$4,hdel,$2,h1,$2,f1
        return "HDEL";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String[] hKeys = getParamsNoKey(args);
        return Reply.integer(cache.hDel(key, hKeys));
    }

}
