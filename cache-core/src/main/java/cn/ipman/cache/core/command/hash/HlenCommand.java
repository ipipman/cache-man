package cn.ipman.cache.core.command.hash;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class HlenCommand implements Command {

    @Override
    public String name() {
        // hlen ===>   *2,$4,hlen,$2,h1
        return "HLEN";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.hLen(key));
    }



}
