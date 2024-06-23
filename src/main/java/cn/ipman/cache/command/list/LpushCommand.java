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
public class LpushCommand implements Command {

    @Override
    public String name() {
        // LPUSH ===> *4,$5,lpush,$1,a,$1,2,$1,3
        return "LPUSH";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String[] vals = getParamsNoKey(args);
        return Reply.integer(cache.lPush(key, vals));
    }


}
