package cn.ipman.cache.command.list;

import cn.ipman.cache.core.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class LpopCommand implements Command {

    @Override
    public String name() {
        // lpop ===> *3,$4,lpop,$2,l1,$1,2
        return "LPOP";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        int count = 1;
        if (args.length > 6) {
            count = Integer.parseInt(getValue(args));
            return Reply.array(cache.lPop(key, count));
        }

        String[] lPop = cache.lPop(key, count);
        return Reply.bulkString(lPop == null || lPop.length == 0 ? null : lPop[0]);

    }


}
