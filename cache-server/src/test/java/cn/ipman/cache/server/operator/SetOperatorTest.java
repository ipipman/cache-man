package cn.ipman.cache.server.operator;

import cn.ipman.cache.core.core.operator.SetOperator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 23:20
 */
public class SetOperatorTest {
    private SetOperator operator;

    @Before
    public void setUp() {
        operator = new SetOperator();
    }

    @Test
    public void testSAdd() {
        String key = "testSet";
        String[] vals = {"val1", "val2", "val3"};
        assertEquals("Expected to add 3 elements", Integer.valueOf("3"), operator.sAdd(key, vals));
    }

    @Test
    public void testSMembers() {
        String key = "testSet";
        String[] vals = {"val1", "val2", "val3"};
        operator.sAdd(key, vals);

        String[] members = operator.sMembers(key);
        assertNotNull("Expected non-null result", members);
        assertEquals("Expected 3 members", 3, members.length);
        assertTrue("Expected member val1", arrayContains(members, "val1"));
        assertTrue("Expected member val2", arrayContains(members, "val2"));
        assertTrue("Expected member val3", arrayContains(members, "val3"));
    }

    @Test
    public void testSCard() {
        String key = "testSet";
        String[] vals = {"val1", "val2", "val3"};
        operator.sAdd(key, vals);

        assertEquals("Expected size 3", Integer.valueOf("3"), operator.sCard(key));

        // Test non-existent key
        assertEquals("Expected size 0 for non-existent key", Integer.valueOf("0"), operator.sCard("nonExistentKey"));
    }

    @Test
    public void testSIsMember() {
        String key = "testSet";
        String[] vals = {"val1", "val2", "val3"};
        operator.sAdd(key, vals);

        assertEquals("Expected sIsMember to return 1 for val1", Integer.valueOf("1"), operator.sIsMember(key, "val1"));
        assertEquals("Expected sIsMember to return 0 for val4", Integer.valueOf("0"), operator.sIsMember(key, "val4"));

        // Test non-existent key
        assertEquals("Expected sIsMember to return 0 for non-existent key", Integer.valueOf("0"), operator.sIsMember("nonExistentKey", "val1"));
    }

    @Test
    public void testSRem() {
        String key = "testSet";
        String[] vals = {"val1", "val2", "val3"};
        operator.sAdd(key, vals);

        assertEquals("Expected to remove 0 elements", Integer.valueOf("0"), operator.sRem(key, new String[]{}));

        assertEquals("Expected to remove 1 element", Integer.valueOf("1"), operator.sRem(key, new String[]{"val1"}));
        String[] members = operator.sMembers(key);
        assertFalse("Expected val1 to be removed", arrayContains(members, "val1"));

        // Test removing non-existent values
        assertEquals("Expected to remove 0 elements", Integer.valueOf("0"), operator.sRem(key, new String[]{"val4"}));
    }

    @Test
    public void testSPop() {
        String key = "testSet";
        String[] vals = {"val1", "val2", "val3"};
        operator.sAdd(key, vals);

        String[] popped = operator.sPop(key, 2);
        assertNotNull("Expected non-null result", popped);
        assertEquals("Expected 2 elements to be popped", 2, popped.length);

        // Test popping more elements than exist
        popped = operator.sPop(key, 10);
        assertNotNull("Expected non-null result", popped);
        assertEquals("Expected empty array after popping all elements", 1, popped.length);
    }

    private boolean arrayContains(String[] array, String value) {
        for (String element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
