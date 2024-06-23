package cn.ipman.cache.core.operator;

import cn.ipman.cache.core.AbstractOperator;

import java.util.Arrays;
import java.util.Objects;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 19:04
 */
public class CommonOperator extends AbstractOperator {

    public int del(String... keys) {
        return keys == null ? 0 : (int) Arrays.stream(keys)
                .map(map::remove).filter(Objects::nonNull).count();
    }

    public int exists(String... keys) {
        return keys == null ? 0 : (int) Arrays.stream(keys)
                .map(map::containsKey).filter(x -> x).count();
    }


    public int ttl(String key) {
        CacheEntry<?> entry = getCacheEntry(key);
        if (entry == null) return -2;       // key 不存在, 返回-2
        if (entry.getTtl() == CacheEntry.DEFAULT_TTL) return -1; // key 没有过期时间

        long ret = (entry.getTs() + entry.getTtl() - System.currentTimeMillis()) / 1000;
        if (ret > 0) return (int) ret;
        return -1;
    }

}
