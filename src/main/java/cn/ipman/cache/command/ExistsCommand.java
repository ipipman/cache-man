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
