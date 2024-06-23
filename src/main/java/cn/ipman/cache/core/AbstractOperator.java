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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CacheEntry<T> {
        private T value;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ZSetEntry {
        private String value;
        private double score;
    }

}
