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
public class HgetCommand implements Command {

    @Override
    public String name() {
        // hget ===>  *3,$4,hget,$2,h1,$2,f2
        return "HGET";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String hKey = getValue(args);
        return Reply.bulkString(cache.hGet(key, hKey));
    }



}
