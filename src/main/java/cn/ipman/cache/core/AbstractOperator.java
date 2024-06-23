package cn.ipman.cache.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 19:02
 */
public abstract class AbstractOperator {

    public static Map<String, CacheEntry<?>> map = new HashMap<>();

    protected Map<String, CacheEntry<?>> getMap() {
        return map;
    }

    protected CacheEntry<?> getCacheEntry(String key) {
        return map.get(key);
    }

    public boolean checkInvalid(String key) {
        CacheEntry<?> entry = getCacheEntry(key);
        if (entry == null || entry.getValue() == null) return true;
        long current = System.currentTimeMillis();
        // 如果key已过期,访问时删除
        if (entry.getTtl() > 0 && (current - entry.getTs()) > entry.getTtl()) {
            System.out.printf("KEY[%s] expire cause CURRENT[%d]-TS[%d] > TTL[%d] ms%n",
                    key, current, entry.getTs(), entry.getTtl());
            map.remove(key);
            return true;
        }
        return false;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CacheEntry<T> {
        private T value;
        private long ts;
        private long ttl;

        public final static long DEFAULT_TTL = -1000L;

        public CacheEntry(T v) {
            value = v;
            ts = System.currentTimeMillis();    // created timestamp
            ttl = DEFAULT_TTL;                  // default alive ttl
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ZSetEntry {
        private String value;
        private double score;
    }

}
