package cn.ipman.cache.core.operator;

import cn.ipman.cache.core.AbstractOperator;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 19:07
 */
public class HashOperator extends AbstractOperator {

    @SuppressWarnings("unchecked")
    public Integer hSet(String key, String[] hKeys, String[] hVals) {
        if (hKeys == null || hKeys.length == 0) return 0;
        if (hVals == null || hVals.length == 0) return 0;
        if (hKeys.length != hVals.length) throw new RuntimeException("hkeys and hvals must be same length");

        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedHashMap<>());
            map.put(key, entry);
        }
        LinkedHashMap<String, String> exist = entry.getValue();
        for (int i = 0; i < hKeys.length; i++) {
            exist.put(hKeys[i], hVals[i]);
        }
        return hKeys.length;
    }


    @SuppressWarnings("unchecked")
    public String hGet(String key, String hKey) {
        if (checkInvalid(key)) return null;
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return null;
        LinkedHashMap<String, String> exist = entry.getValue();
        return exist.get(hKey);
    }


    @SuppressWarnings("unchecked")
    public String[] hGetAll(String key) {
        if (checkInvalid(key)) return null;
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return null;
        LinkedHashMap<String, String> exist = entry.getValue();
        return exist.entrySet().stream()
                .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                .toArray(String[]::new);
    }

    @SuppressWarnings("unchecked")
    public String[] hMGet(String key, String[] hKeys) {
        if (checkInvalid(key)) return null;
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return null;

        LinkedHashMap<String, String> exist = entry.getValue();
        return hKeys == null ? new String[0] : Arrays.stream(hKeys)
                .map(exist::get).toArray(String[]::new);
    }

    @SuppressWarnings("unchecked")
    public Integer hLen(String key) {
        if (checkInvalid(key)) return 0;
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashMap<String, String> exist = entry.getValue();
        return exist.size();
    }


    @SuppressWarnings("unchecked")
    public Integer hExists(String key, String hKey) {
        if (checkInvalid(key)) return 0;
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashMap<String, String> exist = entry.getValue();
        return exist.containsKey(hKey) ? 1 : 0;
    }

    @SuppressWarnings("unchecked")
    public Integer hDel(String key, String[] hKeys) {
        if (checkInvalid(key)) return 0;
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return 0;

        LinkedHashMap<String, String> exist = entry.getValue();
        return hKeys == null ? 0 : (int) Arrays.stream(hKeys)
                .map(exist::remove).filter(Objects::nonNull).count();
    }

}
