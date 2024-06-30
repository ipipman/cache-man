package cn.ipman.cache.core.command.string;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class GetCommand implements Command {

    @Override
    public String name() {
        // GET ===> *2,$3,get,$1,a
        return "GET";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.string(cache.get(key));
    }
}
