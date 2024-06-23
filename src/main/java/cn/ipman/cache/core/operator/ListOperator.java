package cn.ipman.cache.core.operator;

import cn.ipman.cache.core.AbstractOperator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 19:17
 */
public class ListOperator extends AbstractOperator {


    @SuppressWarnings("unchecked")
    public Integer lPush(String key, String[] vals) {
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedList<>());
            map.put(key, entry);
        }
        LinkedList<String> exist = entry.getValue();
        Arrays.stream(vals).forEach(exist::addFirst);
        return vals.length;
    }


    @SuppressWarnings("unchecked")
    public String[] lPop(String key, int count) {
        if (checkInvalid(key)) return null;
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
            map.put(key, entry);
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
        if (checkInvalid(key)) return null;
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
        if (checkInvalid(key)) return 0;
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) return 0;
        LinkedList<String> exist = entry.getValue();
        if (exist == null) return 0;
        return exist.size();
    }

    @SuppressWarnings("unchecked")
    public String lindex(String key, int index) {
        if (checkInvalid(key)) return null;
        CacheEntry<LinkedList<String>> entry = (CacheEntry<LinkedList<String>>) map.get(key);
        if (entry == null) return null;
        LinkedList<String> exist = entry.getValue();
        if (exist == null) return null;
        if (index >= exist.size()) return null;
        return exist.get(index);
    }


    @SuppressWarnings("unchecked")
    public String[] lrange(String key, int start, int end) {
        if (checkInvalid(key)) return null;
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
}
