package cn.ipman.cache.core.command.set;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class SremCommand implements Command {

    @Override
    public String name() {
        // srem ===> *3,$4,srem,$2,s1,$1,1
        return "SREM";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String[] vals = getParamsNoKey(args);
        return Reply.integer(cache.sRem(key, vals));
    }

}
