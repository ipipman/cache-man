package cn.ipman.cache.test.jedis;

import cn.ipman.cache.server.CacheServerApplication;
import cn.ipman.cache.test.jedis.jedis.JedisUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest(classes = {CacheTestJedisApplication.class})
class CacheTestJedisApplicationTests {

    static ApplicationContext context1;

    @Autowired
    JedisUtil jedisUtil;

    @BeforeAll
    @SneakyThrows
    static void init() {
        System.out.println(" ================================ ");
        System.out.println(" ============  6379 ============= ");
        System.out.println(" ================================ ");
        System.out.println(" ================================ ");
        context1 = SpringApplication.run(CacheServerApplication.class);
    }

    @Test
    void test_set_get() {
        jedisUtil.set("test", "test", 0);
        Assertions.assertEquals("test", jedisUtil.get("test", 0));
        Assertions.assertEquals(1L, jedisUtil.del(0, "test"));
    }

    @Test
    void test_set_get_expire() throws InterruptedException {
        jedisUtil.set("test", "test", 0);
        jedisUtil.expire("test", 1, 0);
        Thread.sleep(2000L);
        Assertions.assertNull(jedisUtil.get("test", 0));
    }

    @AfterAll
    static void destroy() {
        System.out.println(" ===========     close spring context     ======= ");
        SpringApplication.exit(context1, () -> 1);
    }

}
