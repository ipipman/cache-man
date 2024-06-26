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
public class LlenCommand implements Command {

    @Override
    public String name() {
        // LLEN ===> *2,$4,llen,$2,l1
        return "LLEN";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        return Reply.integer(cache.lLen(key));
    }


}
