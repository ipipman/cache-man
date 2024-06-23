package cn.ipman.cache.command.string;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class DecrCommand implements Command {

    @Override
    public String name() {
        // DECR ===>  *2,$4,decr,$1,a
        return "DECR";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        try {
            return Reply.integer(cache.decr(key));
        } catch (NumberFormatException e) {
            return Reply.error("NFE " + key + " value is not integer");
        }
    }
}
