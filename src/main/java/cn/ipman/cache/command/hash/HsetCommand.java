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
public class HsetCommand implements Command {

    @Override
    public String name() {
        // hset ===> *6,$4,hset,$2,h1,$2,f1,$3,100,$2,f2,$3,200
        return "HSET";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String[] hKeys = getHKeys(args);
        String[] hVals = getHValues(args);
        return Reply.integer(cache.hSet(key, hKeys, hVals));
    }



}
