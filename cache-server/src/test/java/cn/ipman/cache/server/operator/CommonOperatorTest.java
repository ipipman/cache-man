package cn.ipman.cache.server.operator;

import cn.ipman.cache.core.core.AbstractOperator;
import cn.ipman.cache.core.core.operator.CommonOperator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 23:09
 */
public class CommonOperatorTest {
    private CommonOperator operator;

    @Before
    public void setUp() {
        operator = new CommonOperator();
        // Assume that operator.map is initialized here
    }

    @Test
    public void testDelWithNullKeys() {
        assertEquals("Expected 0 when keys are null", 0, operator.del((String[]) null));
    }

    @Test
    public void testDelWithEmptyKeys() {
        assertEquals("Expected 0 when keys are empty", 0, operator.del());
    }

    @Test
    public void testExistsWithNullKeys() {
        assertEquals("Expected 0 when keys are null", 0, operator.exists((String[]) null));
    }

    @Test
    public void testExistsWithEmptyKeys() {
        assertEquals("Expected 0 when keys are empty", 0, operator.exists());
    }

    @Test
    public void testExpireWithNonExistentKey() {
        assertEquals("Expected 0 when key does not exist", 0, operator.expire("nonexistentKey", 10));
    }

    @Test
    public void testExpireWithValidKey() {
        // Assume that operator.map contains a key "key1"
        assertEquals("Expected 1 when expiring an existing key", 1, operator.expire("key1", 10));
    }

    @Test
    public void testTtlWithNonExistentKey() {
        assertEquals("Expected -2 when key does not exist", -2, operator.ttl("nonexistentKey"));
    }

    @Test
    public void testTtlWithKeyHavingDefaultTtl() {
        // Assume that operator.map contains a key "key1" with default ttl
        assertEquals("Expected -1 when key has default ttl", -1, operator.ttl("key1"));
    }

    @Test
    public void testTtlWithValidKey() {
        // Assume that operator.map contains a key "key1" with a custom ttl and timestamp
        long current = System.currentTimeMillis();
        long ttl = 10;
        long ts = current - (ttl * 1000L);
        AbstractOperator.CacheEntry<Object> entry = new AbstractOperator.CacheEntry<>(null, ttl, ts);
        operator.map.put("key1", entry);

        long expectedTtl = (ts + ttl * 1000L - current) / 1000;
        if (expectedTtl > 0) {
            assertEquals("Expected ttl to be greater than 0 for valid key", (int) expectedTtl, operator.ttl("key1"));
        } else {
            assertEquals("Expected -1 when key has expired", -1, operator.ttl("key1"));
        }
    }
}
