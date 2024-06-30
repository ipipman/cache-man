package cn.ipman.cache.core.core.operator;


import cn.ipman.cache.core.core.AbstractOperator;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 19:10
 */
public class ZSetOperator extends AbstractOperator {


    @SuppressWarnings("unchecked")
    public Integer zAdd(String key, String[] vals, double[] scores) {
        if (vals == null || vals.length == 0) return 0;
        if (scores == null || scores.length == 0) return 0;
        if (vals.length != scores.length) throw new RuntimeException("vals and scores must be same length");

        CacheEntry<LinkedHashSet<ZSetEntry>> entry = (CacheEntry<LinkedHashSet<ZSetEntry>>) map.get(key);
        if (entry == null) {
            entry = new CacheEntry<>(new LinkedHashSet<>());
            map.put(key, entry);
        }

        LinkedHashSet<ZSetEntry> exist = entry.getValue();
        for (int i = 0; i < vals.length; i++) {
            exist.add(new ZSetEntry(vals[i], scores[i]));
        }
        return exist.size();
    }

    public Integer zCard(String key) {
        if (checkInvalid(key)) return 0;
        CacheEntry<?> entry = map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<?> exist = (LinkedHashSet<?>) entry.getValue();
        return exist.size();
    }


    @SuppressWarnings("unchecked")
    public Integer zCount(String key, double min, double max) {
        if (checkInvalid(key)) return 0;
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
        if (checkInvalid(key)) return null;
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
        if (checkInvalid(key)) return -1;
        CacheEntry<LinkedHashSet<ZSetEntry>> entry = (CacheEntry<LinkedHashSet<ZSetEntry>>) map.get(key);
        if (entry == null) return -1;
        LinkedHashSet<ZSetEntry> exist = entry.getValue();

        Double source = zScore(key, val);
        if (source == null) return -1;
        return (int) exist.stream().filter(x -> x.getScore() < source).count();
    }

    @SuppressWarnings("unchecked")
    public Integer zRem(String key, String[] vals) {
        if (checkInvalid(key)) return 0;
        CacheEntry<LinkedHashSet<ZSetEntry>> entry = (CacheEntry<LinkedHashSet<ZSetEntry>>) map.get(key);
        if (entry == null) return 0;
        LinkedHashSet<ZSetEntry> exist = entry.getValue();
        return vals == null ? 0 : (int) Arrays.stream(vals)
                .map(x -> exist.removeIf(y -> y.getValue().equals(x)))
                .filter(x -> x)
                .count();
    }
}
