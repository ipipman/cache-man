package cn.ipman.cache.command.hash;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class HmgetCommand implements Command {

    @Override
    public String name() {
        // hmget ===> *5,$5,hmget,$2,h1,$2,f1,$2,f2,$2,f3
        // return this.getClass().getSimpleName().replace("Command", "").toUpperCase();
        return "HMGET";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String[] hKeys = getParamsNoKey(args);
        return Reply.array(cache.hMGet(key, hKeys));
    }



}
