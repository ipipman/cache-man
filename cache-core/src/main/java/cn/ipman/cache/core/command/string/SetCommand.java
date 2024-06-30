package cn.ipman.cache.core.command.string;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class SetCommand implements Command {

    @Override
    public String name() {
        // SET ===> *3,$3,set,$1,a,$1,1
        return "SET";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String val = getValue(args);
        cache.set(key, val);
        return Reply.string(OK);
    }
}
