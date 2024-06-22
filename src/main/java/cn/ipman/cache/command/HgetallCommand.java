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
public class HgetallCommand implements Command {

    @Override
    public String name() {
        // hgetall ===> *2,$7,hgetall,$2,h1
        return "HGETALL";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.array(cache.hGetall(key));
    }



}
