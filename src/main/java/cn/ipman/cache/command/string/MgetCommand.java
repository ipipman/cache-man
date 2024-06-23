package cn.ipman.cache.command.string;

import cn.ipman.cache.core.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class MgetCommand implements Command {

    @Override
    public String name() {
        // MGET ===> *4,$4,mget,$1,a,$1,b,$1,c
        return "MGET";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String[] keys = getParams(args);
        return Reply.array(cache.mGet(keys));
    }
}
