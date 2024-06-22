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
public class HexistsCommand implements Command {

    @Override
    public String name() {
        // hget ===>  *3,$4,hget,$2,h1,$2,f2
        return "HEXISTS";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String hKey = getValue(args);
        return Reply.integer(cache.hExists(key, hKey));
    }



}
