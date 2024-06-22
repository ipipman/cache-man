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
public class SpopCommand implements Command {

    @Override
    public String name() {
        // LPUSH ===> *4,$5,lpush,$1,a,$1,2,$1,3
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
