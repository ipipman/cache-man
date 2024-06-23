package cn.ipman.cache.command.hash;

import cn.ipman.cache.core.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

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
