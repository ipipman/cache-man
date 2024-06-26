package cn.ipman.cache.core.command.zset;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class ZrankCommand implements Command {

    @Override
    public String name() {
        // zrank ===> *3,$5,zrank,$1,z,$1,b
        return "ZRANK";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String val = getValue(args);
        return Reply.integer(cache.zRank(key, val));
    }


}
