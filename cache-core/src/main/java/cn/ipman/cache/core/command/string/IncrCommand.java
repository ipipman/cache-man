package cn.ipman.cache.core.command.string;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

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
