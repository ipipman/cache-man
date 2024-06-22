package cn.ipman.cache.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.StaticResourceRequest;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Stream;

/**
 * cache entries.
 *
 * @Author IpMan
 * @Date 2024/6/15 20:11
 */
public class IMCache {

    Map<String, CacheEntry<?>> map = new HashMap<>();

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

    // ========================= string start ==========================

    @SuppressWarnings("unchecked")
    public String get(String key) {
        CacheEntry<String> cacheEntry = (CacheEntry<String>) map.get(key);
        return cacheEntry.getValue();
    }

    public void set(String key, String value) {
        map.put(key, new CacheEntry<>(value));
    }

    public int del(String... keys) {
        return keys == null ? 0 : (int) Arrays.stream(keys)
                .map(map::remove).filter(Objects::nonNull).count();
    }

    public Integer strlen(String key) {
        return get(key) == null ? 0 : get(key).length();
    }

    public int exists(String... keys) {
        return keys == null ? 0 : (int) Arrays.stream(keys)
                .map(map::containsKey).filter(x -> x).count();
    }

    public String[] mGet(String... keys) {
        return keys == null ? new String[0] : Arrays.stream(keys)
                .map(this::get).toArray(String[]::new);
    }

    public void mSet(String[] keys, String[] vals) {
        if (keys == null || keys.length == 0) return;
        for (int i = 0; i < keys.length; i++) {
            set(keys[i], vals[i]);
        }
    }

    public int incr(String key) {
        String str = get(key);
        int val = 0;
        try {
            if (str != null) {
                val = Integer.parseInt(str);
            }
            val++;
            set(key, String.valueOf(val));
        } catch (NumberFormatException nfe) {
            throw nfe;
        }
        return val;
    }

    public int decr(String key) {
        String str = get(key);
        int val = 0;
        try {
            if (str != null) {
                val = Integer.parseInt(str);
            }
            val--;
            set(key, String.valueOf(val));
        } catch (NumberFormatException nfe) {
            throw nfe;
        }
        return val;
    }
    // ========================= string end ==========================


    // ========================= list start ==========================

    @SuppressWarnings("unchecked")
    public Integer lPush(String key, String[] vals) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedList<>());
            this.map.put(key, entry);
        }
        LinkedList<String> exist = entry.getValue();
        Arrays.stream(vals).forEach(exist::addFirst);
        return vals.length;
    }


    @SuppressWarnings("unchecked")
    public String[] lPop(String key, int count) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) return null;
        LinkedList<String> exist = entry.getValue();
        if (exist == null) return null;

        int len = Math.min(count, exist.size());
        String[] ret = new String[len];
        int index = 0;
        while (index < len) {
            ret[index++] = exist.pollFirst();
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public Integer rPush(String key, String[] vals) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedList<>());
            this.map.put(key, entry);
        }
        LinkedList<String> exist = entry.getValue();
        if (vals.length == 0) {
            return 0;
        }
        exist.addAll(List.of(vals));
        return vals.length;
    }

    @SuppressWarnings("unchecked")
    public String[] rPop(String key, int count) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) return null;
        LinkedList<String> exist = entry.getValue();
        if (exist == null) return null;

        int len = Math.min(count, exist.size());
        String[] ret = new String[len];
        int index = 0;
        while (index < len) {
            ret[index++] = exist.removeLast();
        }
        return ret;
    }


    @SuppressWarnings("unchecked")
    public Integer llen(String key) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) return 0;
        LinkedList<String> exist = entry.getValue();
        if (exist == null) return 0;
        return exist.size();
    }

    @SuppressWarnings("unchecked")
    public String lindex(String key, int index) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) return null;
        LinkedList<String> exist = entry.getValue();
        if (exist == null) return null;
        if (index >= exist.size()) return null;
        return exist.get(index);
    }


    @SuppressWarnings("unchecked")
    public String[] lrange(String key, int start, int end) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) return null;
        LinkedList<String> exist = entry.getValue();
        if (exist == null) return null;

        int size = exist.size();
        if (start >= size) return null;
        if (end >= size) {
            end = size - 1;
        }

        int len = Math.min(size, end - start + 1);
        String[] ret = new String[len];
        for (int i = 0; i < len; i++) {
            ret[i] = exist.get(start + i);
        }
        return ret;
    }

    // ========================= list end ==========================


    // ========================= set start ==========================
    @SuppressWarnings("unchecked")
    public Integer sadd(String key, String[] vals) {
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedHashSet<>());
            this.map.put(key, entry);
        }
        LinkedHashSet<String> exist = entry.getValue();
        if (vals == null || vals.length == 0) {
            return 0;
        }
        exist.addAll(Arrays.asList(vals));
        return vals.length;
    }


    @SuppressWarnings("unchecked")
    public String[] smembers(String key) {
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return null;
        LinkedHashSet<String> exist = entry.getValue();
        if (exist == null) return null;
        return exist.toArray(String[]::new);
    }

    @SuppressWarnings("unchecked")
    public Integer scard(String key) {
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<String> exist = entry.getValue();
        if (exist == null) return 0;
        return exist.size();
    }

    @SuppressWarnings("unchecked")
    public Integer sismember(String key, String val) {
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<String> exist = entry.getValue();
        return exist.contains(val) ? 1 : 0;
    }

    @SuppressWarnings("unchecked")
    public Integer srem(String key, String[] vals) {
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<String> exist = entry.getValue();
        return vals == null ? 0 : (int) Arrays.stream(vals)
                .map(exist::remove).filter(x -> x).count();
    }

    Random random = new Random();

    @SuppressWarnings("unchecked")
    public String[] sPop(String key, int count) {
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return null;
        LinkedHashSet<String> exist = entry.getValue();
        if (exist == null) return null;

        int len = Math.min(count, exist.size());
        String[] ret = new String[len];
        int index = 0; // spop 是随机移除
        while (index < len) {
            String[] array = exist.toArray(String[]::new);
            String obj = array[random.nextInt(exist.size())];
            exist.remove(obj);
            ret[index++] = obj;
        }
        return ret;
    }

    // ========================= set end ==========================


    // ========================= hash start ==========================
    @SuppressWarnings("unchecked")
    public Integer hSet(String key, String[] hKeys, String[] hVals) {
        if (hKeys == null || hKeys.length == 0) return 0;
        if (hVals == null || hVals.length == 0) return 0;
        if (hKeys.length != hVals.length) throw new RuntimeException("hkeys and hvals must be same length");

        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedHashMap<>());
            this.map.put(key, entry);
        }
        LinkedHashMap<String, String> exist = entry.getValue();
        for (int i = 0; i < hKeys.length; i++) {
            exist.put(hKeys[i], hVals[i]);
        }
        return hKeys.length;
    }


    @SuppressWarnings("unchecked")
    public String hGet(String key, String hKey) {
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return null;
        LinkedHashMap<String, String> exist = entry.getValue();
        return exist.get(hKey);
    }


    @SuppressWarnings("unchecked")
    public String[] hGetall(String key) {
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return null;
        LinkedHashMap<String, String> exist = entry.getValue();
        return exist.entrySet().stream()
                .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                .toArray(String[]::new);
    }

    @SuppressWarnings("unchecked")
    public String[] hMGet(String key, String[] hKeys) {
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return null;

        LinkedHashMap<String, String> exist = entry.getValue();
        return hKeys == null ? new String[0] : Arrays.stream(hKeys)
                .map(exist::get).toArray(String[]::new);
    }

    @SuppressWarnings("unchecked")
    public Integer hLen(String key) {
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashMap<String, String> exist = entry.getValue();
        return exist.size();
    }


    @SuppressWarnings("unchecked")
    public Integer hExists(String key, String hKey) {
        if (hKey == null) return 0;
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashMap<String, String> exist = entry.getValue();
        return exist.containsKey(hKey) ? 1 : 0;
    }

    @SuppressWarnings("unchecked")
    public Integer hDel(String key, String[] hKeys) {
        CacheEntry<LinkedHashMap<String, String>> entry = (CacheEntry<LinkedHashMap<String, String>>) map.get(key);
        if (entry == null) return 0;

        LinkedHashMap<String, String> exist = entry.getValue();
        return hKeys == null ? 0 : (int) Arrays.stream(hKeys)
                .map(exist::remove).filter(Objects::nonNull).count();
    }

    // ========================= hash end ==========================


    // ========================= zset end ==========================

    @SuppressWarnings("unchecked")
    public Integer zAdd(String key, String[] vals, double[] scores) {
        if (vals == null || vals.length == 0) return 0;
        if (scores == null || scores.length == 0) return 0;
        if (vals.length != scores.length) throw new RuntimeException("vals and scores must be same length");

        CacheEntry<LinkedHashSet<ZSetEntry>> entry = (CacheEntry<LinkedHashSet<ZSetEntry>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedHashSet<>());
            this.map.put(key, entry);
        }

        LinkedHashSet<ZSetEntry> exist = entry.getValue();
        for (int i = 0; i < vals.length; i++) {
            exist.add(new ZSetEntry(vals[i], scores[i]));
        }
        return exist.size();
    }

    public Integer zCard(String key) {
        CacheEntry<?> entry = map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<?> exist = (LinkedHashSet<?>) entry.getValue();
        return exist.size();
    }


    @SuppressWarnings("unchecked")
    public Integer zCount(String key, double min, double max) {
        if (min > max) throw new RuntimeException("min must be less than max");
        CacheEntry<LinkedHashSet<ZSetEntry>> entry = (CacheEntry<LinkedHashSet<ZSetEntry>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<ZSetEntry> exist = entry.getValue();
        return (int) exist.stream()
                .filter(x -> x.getScore() >= min && x.getScore() <= max)
                .count();
    }

    @SuppressWarnings("unchecked")
    public Double zScore(String key, String val) {
        if (val == null) return null;
        CacheEntry<LinkedHashSet<ZSetEntry>> entry = (CacheEntry<LinkedHashSet<ZSetEntry>>) map.get(key);
        if (entry == null) return null;
        LinkedHashSet<ZSetEntry> exist = entry.getValue();
        return exist.stream()
                .filter(x -> x.getValue().equals(val))
                .map(ZSetEntry::getScore)
                .findFirst()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    public Integer zRank(String key, String val) {
        CacheEntry<LinkedHashSet<ZSetEntry>> entry = (CacheEntry<LinkedHashSet<ZSetEntry>>) map.get(key);
        if (entry == null) return null;
        LinkedHashSet<ZSetEntry> exist = entry.getValue();

        Double source = zScore(key, val);
        if (source == null) return null;
        return (int) exist.stream().filter(x -> x.getScore() < source).count();
    }

    @SuppressWarnings("unchecked")
    public Integer zRem(String key, String[] vals) {
        CacheEntry<LinkedHashSet<ZSetEntry>> entry = (CacheEntry<LinkedHashSet<ZSetEntry>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<ZSetEntry> exist = entry.getValue();
        return vals == null ? 0 : (int) Arrays.stream(vals)
                .map(x -> exist.removeIf(y -> y.getValue().equals(x)))
                .filter(x -> x)
                .count();
    }


    // ========================= zset end ==========================
}
