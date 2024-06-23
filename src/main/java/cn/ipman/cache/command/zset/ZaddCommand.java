package cn.ipman.cache.command.zset;

import cn.ipman.cache.command.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

import java.util.Arrays;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class ZaddCommand implements Command {

    @Override
    public String name() {
        // zadd ===> *8,$4,zadd,$1,z,$3,100,$1,a,$3,200,$1,b,$3,300,$1,c
        return "ZADD";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String[] scores = getHKeys(args);
        String[] vals = getHValues(args);
        return Reply.integer(cache.zAdd(key, vals, toDouble(scores)));
    }

    double[] toDouble(String[] scores) {
        return Arrays.stream(scores).mapToDouble(Double::parseDouble).toArray();
    }
}
