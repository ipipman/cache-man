package cn.ipman.cache.operator;
import cn.ipman.cache.core.operator.StringOperator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 23:30
 */
public class StringOperatorTest {
    private StringOperator operator;

    @Before
    public void setUp() {
        operator = new StringOperator();
    }

    @Test
    public void testGetNonExistentKey() {
        assertNull(operator.get("nonExistentKey"));
    }

    @Test
    public void testSetAndGet() {
        operator.set("key", "value");
        assertEquals("value", operator.get("key"));
    }

    @Test
    public void testStrlen() {
        operator.set("key", "value");
        assertEquals(Integer.valueOf("5"), operator.strlen("key"));
    }

    @Test
    public void testMGet() {
        operator.set("key1", "value1");
        operator.set("key2", "value2");
        String[] result = operator.mGet("key1", "key2");
        assertEquals("value1", result[0]);
        assertEquals("value2", result[1]);
    }

    @Test
    public void testMSet() {
        String[] keys = {"key1", "key2", "key3"};
        String[] values = {"value1", "value2", "value3"};
        operator.mSet(keys, values);

        assertEquals("value1", operator.get("key1"));
        assertEquals("value2", operator.get("key2"));
        assertEquals("value3", operator.get("key3"));
    }

    @Test
    public void testIncr() {
        operator.set("key", "1");
        assertEquals(2, operator.incr("key"));
    }

    @Test
    public void testDecr() {
        operator.set("key", "2");
        assertEquals(1, operator.decr("key"));
    }

    @Test(expected = NumberFormatException.class)
    public void testIncrWithInvalidValue() {
        operator.set("key", "invalid");
        operator.incr("key");
    }

    @Test(expected = NumberFormatException.class)
    public void testDecrWithInvalidValue() {
        operator.set("key", "invalid");
        operator.decr("key");
    }
}
