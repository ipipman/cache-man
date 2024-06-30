package cn.ipman.cache.core.command.common;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

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
        String[] keys = getParams(args);
        return Reply.integer(cache.del(keys));
    }
}
