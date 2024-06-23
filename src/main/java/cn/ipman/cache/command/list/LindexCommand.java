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
public class LindexCommand implements Command {

    @Override
    public String name() {
        // LINDEX ===> *3,$6,lindex,$2,l1,$1,5
        return "LINDEX";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        int index = Integer.parseInt(getValue(args));
        return Reply.string(cache.lindex(key, index));
    }


}
