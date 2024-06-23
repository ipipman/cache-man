package cn.ipman.cache.command.zset;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class ZremCommand implements Command {

    @Override
    public String name() {
        // zrem ===>  *3,$4,zrem,$1,z,$1,c
        return "ZREM";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String[] vals = getParamsNoKey(args);
        return Reply.integer(cache.zRem(key, vals));
    }


}
