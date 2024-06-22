package cn.ipman.cache.command;

import cn.ipman.cache.core.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class DelCommand implements Command {

    @Override
    public String name() {
        // DEL ===> *4,$3,del,$1,a,$1,b,$1,c
        return "DEL";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String[] keys = getKeys(args);
        return Reply.integer(cache.del(keys));
    }
}
