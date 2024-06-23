package cn.ipman.cache.operator;

import cn.ipman.cache.core.operator.ZSetOperator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 23:34
 */

public class ZSetOperatorTest {

    private ZSetOperator operator;

    @Before
    public void setUp() {
        operator = new ZSetOperator();
    }

    @Test
    public void testZAdd() {
        String key = "testKey";
        String[] vals = {"val1", "val2", "val3"};
        double[] scores = {1.0, 2.0, 3.0};

        Integer result = operator.zAdd(key, vals, scores);
        assertEquals(3, result.intValue());

        Integer size = operator.zCard(key);
        assertEquals(3, size.intValue());
    }

    @Test
    public void testZCard() {
        String key = "testKey";
        String[] vals = {"val1", "val2", "val3"};
        double[] scores = {1.0, 2.0, 3.0};

        operator.zAdd(key, vals, scores);
        Integer size = operator.zCard(key);
        assertEquals(3, size.intValue());

        size = operator.zCard("nonExistentKey");
        assertEquals(0, size.intValue());
    }

    @Test
    public void testZCount() {
        String key = "testKey";
        String[] vals = {"val1", "val2", "val3"};
        double[] scores = {1.0, 2.0, 3.0};

        operator.zAdd(key, vals, scores);
        Integer count = operator.zCount(key, 0.0, 2.0);
        assertEquals(2, count.intValue());

        count = operator.zCount(key, 4.0, 5.0);
        assertEquals(0, count.intValue());
    }

    @Test
    public void testZScore() {
        String key = "testKey";
        String[] vals = {"val1", "val2", "val3"};
        double[] scores = {1.0, 2.0, 3.0};

        operator.zAdd(key, vals, scores);
        Double score = operator.zScore(key, "val1");
        assertEquals(1.0, score, 0.0);

        score = operator.zScore(key, "nonExistentVal");
        assertNull(score);
    }

    @Test
    public void testZRank() {
        String key = "testKey";
        String[] vals = {"val1", "val2", "val3"};
        double[] scores = {1.0, 2.0, 3.0};

        operator.zAdd(key, vals, scores);
        Integer rank = operator.zRank(key, "val1");
        assertEquals(0, rank.intValue());

        rank = operator.zRank(key, "nonExistentVal");
        assertEquals(-1, rank.intValue());
    }

    @Test
    public void testZRem() {
        String key = "testKey";
        String[] vals = {"val1", "val2", "val3"};
        double[] scores = {1.0, 2.0, 3.0};

        operator.zAdd(key, vals, scores);
        Integer result = operator.zRem(key, new String[]{"val1", "val3"});
        assertEquals(2, result.intValue());

        Integer size = operator.zCard(key);
        assertEquals(1, size.intValue());
    }
}

