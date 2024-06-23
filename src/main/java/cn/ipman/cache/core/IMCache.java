package cn.ipman.cache.core;

import cn.ipman.cache.core.operator.*;

/**
 * cache entries.
 *
 * @Author IpMan
 * @Date 2024/6/15 20:11
 */
public class IMCache {

    CommonOperator commonOperator = new CommonOperator();
    StringOperator stringOperator = new StringOperator();
    ListOperator listOperator = new ListOperator();
    HashOperator hashOperator = new HashOperator();
    SetOperator setOperator = new SetOperator();
    ZSetOperator zSetOperator = new ZSetOperator();


    // ========================= common start ==========================
    public int del(String... keys) {
        return commonOperator.del(keys);
    }

    public int exists(String... keys) {
        return commonOperator.exists(keys);
    }

    public int ttl(String key) {
        return commonOperator.ttl(key);
    }
    // ========================= common end ==========================


    // ========================= string start ==========================
    public String get(String key) {
        return stringOperator.get(key);
    }

    public void set(String key, String value) {
        stringOperator.set(key, value);
    }

    public Integer strlen(String key) {
        return stringOperator.strlen(key);
    }


    public String[] mGet(String... keys) {
        return stringOperator.mGet(keys);
    }

    public void mSet(String[] keys, String[] vals) {
        stringOperator.mSet(keys, vals);
    }

    public int incr(String key) {
        return stringOperator.incr(key);
    }

    public int decr(String key) {
        return stringOperator.decr(key);
    }
    // ========================= string end ==========================


    // ========================= list start ==========================
    public Integer lPush(String key, String[] vals) {
        return listOperator.lPush(key, vals);
    }

    public String[] lPop(String key, int count) {
        return listOperator.lPop(key, count);
    }

    public Integer rPush(String key, String[] vals) {
        return listOperator.rPush(key, vals);
    }

    public String[] rPop(String key, int count) {
        return listOperator.rPop(key, count);
    }

    public Integer llen(String key) {
        return listOperator.llen(key);
    }

    public String lindex(String key, int index) {
        return listOperator.lindex(key, index);
    }

    public String[] lrange(String key, int start, int end) {
        return listOperator.lrange(key, start, end);
    }
    // ========================= list end ==========================


    // ========================= set start ==========================
    public Integer sadd(String key, String[] vals) {
        return setOperator.sadd(key, vals);
    }

    public String[] smembers(String key) {
        return setOperator.smembers(key);
    }

    public Integer scard(String key) {
        return setOperator.scard(key);
    }

    public Integer sismember(String key, String val) {
        return setOperator.sismember(key, val);
    }

    public Integer srem(String key, String[] vals) {
        return setOperator.srem(key, vals);
    }

    public String[] sPop(String key, int count) {
        return setOperator.sPop(key, count);
    }
    // ========================= set end ==========================


    // ========================= hash start ==========================
    public Integer hSet(String key, String[] hKeys, String[] hVals) {
        return hashOperator.hSet(key, hKeys, hVals);
    }

    public String hGet(String key, String hKey) {
        return hashOperator.hGet(key, hKey);
    }

    public String[] hGetall(String key) {
        return hashOperator.hGetall(key);
    }

    public String[] hMGet(String key, String[] hKeys) {
        return hashOperator.hMGet(key, hKeys);
    }

    public Integer hLen(String key) {
        return hashOperator.hLen(key);
    }

    public Integer hExists(String key, String hKey) {
        return hashOperator.hExists(key, hKey);
    }

    public Integer hDel(String key, String[] hKeys) {
        return hashOperator.hDel(key, hKeys);
    }
    // ========================= hash end ==========================


    // ========================= zSet end ==========================
    public Integer zAdd(String key, String[] vals, double[] scores) {
        return zSetOperator.zAdd(key, vals, scores);
    }

    public Integer zCard(String key) {
        return zSetOperator.zCard(key);
    }

    public Integer zCount(String key, double min, double max) {
        return zSetOperator.zCount(key, min, max);
    }

    public Double zScore(String key, String val) {
        return zSetOperator.zScore(key, val);
    }

    public Integer zRank(String key, String val) {
        return zSetOperator.zRank(key, val);
    }

    public Integer zRem(String key, String[] vals) {
        return zSetOperator.zRem(key, vals);
    }
    // ========================= zset end ==========================
}
