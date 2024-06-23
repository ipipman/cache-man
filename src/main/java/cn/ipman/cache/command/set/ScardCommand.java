package cn.ipman.cache.command.set;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class ScardCommand implements Command {

    @Override
    public String name() {
        // scard ===> *2,$5,scard,$2,s1
        return "SCARD";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.scard(key));
    }


}
