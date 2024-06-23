package cn.ipman.cache.command.set;

import cn.ipman.cache.core.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class SmembersCommand implements Command {

    @Override
    public String name() {
        // smembers ===> *2,$8,smembers,$2,s1
        return "SMEMBERS";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.array(cache.smembers(key));
    }


}