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
public class ExistsCommand implements Command {

    @Override
    public String name() {
        // EXISTS ===>  *2,$6,exists,$1,a
        return "EXISTS";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String[] keys = getParams(args);
        return Reply.integer(cache.exists(keys));
    }
}
