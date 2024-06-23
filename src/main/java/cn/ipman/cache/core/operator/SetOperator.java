package cn.ipman.cache.core.operator;

import cn.ipman.cache.core.AbstractOperator;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 19:13
 */
public class SetOperator extends AbstractOperator {

    @SuppressWarnings("unchecked")
    public Integer sAdd(String key, String[] vals) {
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedHashSet<>());
            map.put(key, entry);
        }
        LinkedHashSet<String> exist = entry.getValue();
        if (vals == null || vals.length == 0) {
            return 0;
        }
        exist.addAll(Arrays.asList(vals));
        return vals.length;
    }


    @SuppressWarnings("unchecked")
    public String[] sMembers(String key) {
        if (checkInvalid(key)) return null;
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return null;
        LinkedHashSet<String> exist = entry.getValue();
        if (exist == null) return null;
        return exist.toArray(String[]::new);
    }

    @SuppressWarnings("unchecked")
    public Integer sCard(String key) {
        if (checkInvalid(key)) return 0;
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<String> exist = entry.getValue();
        if (exist == null) return 0;
        return exist.size();
    }

    @SuppressWarnings("unchecked")
    public Integer sIsMember(String key, String val) {
        if (checkInvalid(key)) return 0;
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<String> exist = entry.getValue();
        return exist.contains(val) ? 1 : 0;
    }

    @SuppressWarnings("unchecked")
    public Integer sRem(String key, String[] vals) {
        if (checkInvalid(key)) return 0;
        CacheEntry<LinkedHashSet<String>> entry = (CacheEntry<LinkedHashSet<String>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<String> exist = entry.getValue();
        return vals == null ? 0 : (int) Arrays.stream(vals)
                .map(exist::remove).filter(x -> x).count();
    }

    Random random = new Random();

    @SuppressWarnings("unchecked")
    public String[] sPop(String key, int count) {
        if (checkInvalid(key)) return null;
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

}
