package cn.ipman.cache.command.string;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class MsetCommand implements Command {

    @Override
    public String name() {
        // MSET ===> *7,$4,mset,$1,a,$1,1,$1,b,$1,2,$1,c,$1,3
        return "MSET";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String[] keys = getKeys(args);
        String[] vals = getValues(args);
        cache.mSet(keys, vals);
        return Reply.string(OK);
    }

}
