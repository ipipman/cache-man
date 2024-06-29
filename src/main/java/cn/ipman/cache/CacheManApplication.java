package cn.ipman.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CacheManApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheManApplication.class, args);
    }

}
