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
public class ZscoreCommand implements Command {

    @Override
    public String name() {
        // scard ===> *2,$5,scard,$2,s1
        return "ZSCORE";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        String key = getKey(args);
        String val = getValue(args);
        Double score = cache.zScore(key, val);
        return Reply.string(score == null ? null : score.toString());
    }


}
