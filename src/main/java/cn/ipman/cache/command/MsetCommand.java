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
public class MsetCommand implements Command {

    @Override
    public String name() {
        // MSET ===> *7,$4,mset,$1,a,$1,1,$1,b,$1,2,$1,c,$1,3
        return "MSET";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        int len = (args.length - 3) / 4;
        String[] keys = new String[len];
        String[] vals = new String[len];
        for (int i = 0; i < len; i++) {
            keys[i] = args[4 + i * 4];
            vals[i] = args[6 + i * 4];
        }
        cache.mSet(keys, vals);
        return Reply.string(OK);
    }
}
