package cn.ipman.cache.operator;

import cn.ipman.cache.core.operator.HashOperator;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static junit.framework.TestCase.*;


/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 23:12
 */
public class HashOperatorTest {
    private HashOperator operator;

    @Before
    public void setUp() {
        operator = new HashOperator();
    }

    @Test
    public void testHSet() {
        String key = "testKey";
        String[] hKeys = {"key1", "key2"};
        String[] hVals = {"val1", "val2"};

        Integer result = operator.hSet(key, hKeys, hVals);
        assertNotNull(result);
        assertEquals(2, result.intValue());

        String[] getAllResult = operator.hGetAll(key);
        assertNotNull(getAllResult);
        assertEquals(4, getAllResult.length);
        assertTrue(Arrays.asList(getAllResult).containsAll(Arrays.asList(hKeys)));
        assertTrue(Arrays.asList(getAllResult).containsAll(Arrays.asList(hVals)));
    }

    @Test
    public void testHGet() {
        String key = "testKey";
        String hKey = "key1";
        String expectedValue = "val1";
        operator.hSet(key, new String[]{"key1"}, new String[]{expectedValue});
        String result = operator.hGet(key, hKey);
        assertEquals(expectedValue, result);
    }

    @Test
    public void testHGetAll() {
        String key = "testKey";
        String[] hKeys = {"key1", "key2"};
        String[] hVals = {"val1", "val2"};
        operator.hSet(key, hKeys, hVals);
        String[] result = operator.hGetAll(key);
        assertNotNull(result);
        assertEquals(4, result.length);
        assertTrue(Arrays.asList(result).containsAll(Arrays.asList(hKeys)));
        assertTrue(Arrays.asList(result).containsAll(Arrays.asList(hVals)));
    }

    @Test
    public void testHMGet() {
        String key = "testKey";
        String[] hKeys = {"key1", "key2"};
        String[] hVals = {"val1", "val2"};
        operator.hSet(key, hKeys, hVals);
        String[] result = operator.hMGet(key, hKeys);
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals("val1", result[0]);
        assertEquals("val2", result[1]);
    }

    @Test
    public void testHLen() {
        String key = "testKey";
        String[] hKeys = {"key1", "key2"};
        String[] hVals = {"val1", "val2"};
        operator.hSet(key, hKeys, hVals);
        Integer result = operator.hLen(key);
        assertNotNull(result);
        assertEquals(2, result.intValue());
    }

    @Test
    public void testHExists() {
        String key = "testKey";
        String hKey = "key1";
        operator.hSet(key, new String[]{hKey}, new String[]{"val1"});
        Integer result = operator.hExists(key, hKey);
        assertNotNull(result);
        assertEquals(1, result.intValue());
    }

    @Test
    public void testHDel() {
        String key = "testKey";
        String[] hKeys = {"key1", "key2"};
        String[] hVals = {"val1", "val2"};
        operator.hSet(key, hKeys, hVals);
        Integer result = operator.hDel(key, hKeys);
        assertNotNull(result);
        assertEquals(2, result.intValue());
        assertNull(operator.hGet(key, hKeys[0]));
        assertNull(operator.hGet(key, hKeys[1]));
    }
}
