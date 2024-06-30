package cn.ipman.cache.core.command.zset;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;
/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class ZcountCommand implements Command {

    @Override
    public String name() {
        // zcount ===> *4,$6,zcount,$1,z,$2,50,$3,500
        return "ZCOUNT";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        double min = Double.parseDouble(getValue(args));
        double max = Double.parseDouble(args[8]);
        return Reply.integer(cache.zCount(key, min, max));
    }


}
