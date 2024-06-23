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
}
