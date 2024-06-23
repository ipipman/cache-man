package cn.ipman.cache.operator;
import cn.ipman.cache.core.operator.CommonOperator;
import cn.ipman.cache.core.operator.ListOperator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 23:15
 */
public class ListOperatorTest {
    private ListOperator operator;
    private CommonOperator commonOperator;

    @Before
    public void setUp() {
        operator = new ListOperator();
        commonOperator = new CommonOperator();
    }

    @Test
    public void testLPush() {
        String key = "listKey";
        commonOperator.del(key);
        String[] vals = {"val1", "val2", "val3"};
        Integer result = operator.lPush(key, vals);
        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    public void testLPop() {
        String key = "listKey";
        commonOperator.del(key);
        String[] vals = {"val1", "val2", "val3"};
        operator.lPush(key, vals);
        String[] result = operator.lPop(key, 2);
        assertNotNull(result);
        assertEquals(2, result.length);
    }

    @Test
    public void testRPush() {
        String key = "listKey";
        commonOperator.del(key);
        String[] vals = {"val1", "val2", "val3"};
        Integer result = operator.rPush(key, vals);
        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    public void testRPop() {
        String key = "listKey";
        commonOperator.del(key);
        String[] vals = {"val1", "val2", "val3"};
        operator.rPush(key, vals);
        String[] result = operator.rPop(key, 2);
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals("val3", result[0]);
        assertEquals("val2", result[1]);
    }

    @Test
    public void testLLen() {
        String key = "listKey";
        commonOperator.del(key);
        String[] vals = {"val1", "val2", "val3"};
        operator.rPush(key, vals);
        Integer result = operator.lLen(key);
        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    public void testLIndex() {
        String key = "listKey";
        commonOperator.del(key);
        String[] vals = {"val1", "val2", "val3"};
        operator.rPush(key, vals);
        String result = operator.lIndex(key, 1);
        assertEquals("val2", result);
    }

    @Test
    public void testLRange() {
        String key = "listKey";
        commonOperator.del(key);
        String[] vals = {"val1", "val2", "val3"};
        operator.rPush(key, vals);
        String[] result = operator.lRange(key, 0, 1);
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals("val1", result[0]);
        assertEquals("val2", result[1]);
    }
}
