package cn.ipman.cache.core.command.list;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class RpopCommand implements Command {

    @Override
    public String name() {
        // rpop ===> *3,$4,rpop,$2,l1,$1,2
        return "RPOP";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        int count = 1;
        if (args.length > 6) {
            count = Integer.parseInt(getValue(args));
            return Reply.array(cache.rPop(key, count));
        }

        String[] rPop = cache.rPop(key, count);
        return Reply.string(rPop == null || rPop.length == 0 ? null : rPop[0]);

    }


}
