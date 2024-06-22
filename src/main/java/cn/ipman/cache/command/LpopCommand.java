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
public class LpopCommand implements Command {

    @Override
    public String name() {
        // lpop ===> *3,$3,set,$1,a,$1,1
        return "LPOP";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        int count = 1;
        if (args.length > 6) {
            count = Integer.parseInt(getValue(args));
            return Reply.array(cache.lPpop(key, count));
        }

        String[] lPop = cache.lPpop(key, count);
        return Reply.string(lPop == null ? null : lPop[0]);

    }


}
