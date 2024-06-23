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
public class ZcardCommand implements Command {

    @Override
    public String name() {
        // zcard ===> *2,$5,zcard,$1,z
        return "ZCARD";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.zCard(key));
    }


}
