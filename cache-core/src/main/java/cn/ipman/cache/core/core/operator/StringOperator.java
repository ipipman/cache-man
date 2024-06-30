package cn.ipman.cache.core.core.operator;


import cn.ipman.cache.core.core.AbstractOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 19:14
 */
public class StringOperator extends AbstractOperator {

    @SuppressWarnings("unchecked")
    public String get(String key) {
        if (checkInvalid(key)) return null;
        CacheEntry<String> cacheEntry = (CacheEntry<String>) map.get(key);
        if (cacheEntry == null) return null;
        return cacheEntry.getValue();
    }

    public void set(String key, String value) {
        map.put(key, new CacheEntry<>(value));
    }

    public Integer strlen(String key) {
        if (checkInvalid(key)) return 0;
        return get(key) == null ? 0 : get(key).length();
    }


    public String[] mGet(String... keys) {
        if (keys == null) return new String[0];
        List<String> ret = new ArrayList<>();
        for (String key : keys) {
            if (!checkInvalid(key)) {
                ret.add(this.get(key));
            }
        }
        return ret.toArray(new String[0]);

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

}
