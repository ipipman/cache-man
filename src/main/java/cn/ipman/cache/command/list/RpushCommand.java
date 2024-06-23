package cn.ipman.cache.command.list;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class RpushCommand implements Command {

    @Override
    public String name() {
        // rPUSH ===> *4,$5,rpush,$1,a,$1,2,$1,3
        return "RPUSH";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String[] vals = getParamsNoKey(args);
        return Reply.integer(cache.rPush(key, vals));
    }


}
