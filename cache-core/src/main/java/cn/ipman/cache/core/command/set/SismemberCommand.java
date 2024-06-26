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
public class SismemberCommand implements Command {

    @Override
    public String name() {
        // sismember ===> *3,$9,sismember,$2,s1,$1,2
        return "SISMEMBER";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String val = getValue(args);
        return Reply.integer(cache.sIsMember(key, val));
    }


}
