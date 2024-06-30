package cn.ipman.cache.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Created by ipipman on 2021/1/6.
 *
 * @version V1.0
 * @Package com.ipman.springboot.redis.jedis.sample.config
 * @Description: (用一句话描述该文件做什么)
 * @date 2021/1/6 10:11 下午
 */
@Configuration
public class JedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.timeout}")
    private int timeout;

    @Bean
    @ConfigurationProperties("spring.data.redis")
    public JedisPoolConfig jedisPoolConfig() {
        System.out.println("jedisPoolConfig staring...");
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool() {
        return new JedisPool(jedisPoolConfig(), host, port, timeout * 1000);
    }
}
