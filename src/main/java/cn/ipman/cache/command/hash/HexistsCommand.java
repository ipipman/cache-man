package cn.ipman.cache.command.hash;

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
        // hexists ===> *3,$7,hexists,$2,h1,$2,f3
        return "HEXISTS";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String hKey = getValue(args);
        return Reply.integer(cache.hExists(key, hKey));
    }



}
