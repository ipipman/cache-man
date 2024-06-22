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
public class HmgetCommand implements Command {

    @Override
    public String name() {
        // hget ===>  *3,$4,hget,$2,h1,$2,f2
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
