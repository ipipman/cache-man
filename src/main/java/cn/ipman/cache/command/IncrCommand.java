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
public class IncrCommand implements Command {

    @Override
    public String name() {
        // INCR ===> *3,$4,incr,$1,a,$1,1
        return "INCR";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        try {
            return Reply.integer(cache.incr(key));
        } catch (NumberFormatException e) {
            return Reply.error("NFE " + key + " value is not integer");
        }
    }
}
