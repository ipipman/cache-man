package cn.ipman.cache.core.command.set;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class SpopCommand implements Command {

    @Override
    public String name() {
        // spop ===> *3,$4,spop,$2,s1,$1,1
        return "SPOP";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        int count = 1;
        if (args.length > 6) {
            count = Integer.parseInt(getValue(args));
            return Reply.array(cache.sPop(key, count));
        }

        String[] sPop = cache.sPop(key, count);
        return Reply.bulkString(sPop == null || sPop.length == 0 ? null : sPop[0]);
    }


}
