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

    public int expire(String key, long ttl) {
        return commonOperator.expire(key, ttl);
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

    public Integer lLen(String key) {
        return listOperator.lLen(key);
    }

    public String lIndex(String key, int index) {
        return listOperator.lIndex(key, index);
    }

    public String[] lRange(String key, int start, int end) {
        return listOperator.lRange(key, start, end);
    }
    // ========================= list end ==========================


    // ========================= set start ==========================
    public Integer sAdd(String key, String[] vals) {
        return setOperator.sAdd(key, vals);
    }

    public String[] sMembers(String key) {
        return setOperator.sMembers(key);
    }

    public Integer sCard(String key) {
        return setOperator.sCard(key);
    }

    public Integer sIsMember(String key, String val) {
        return setOperator.sIsMember(key, val);
    }

    public Integer sRem(String key, String[] vals) {
        return setOperator.sRem(key, vals);
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

    public String[] hGetAll(String key) {
        return hashOperator.hGetAll(key);
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
