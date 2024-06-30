package cn.ipman.cache.server.config;

import cn.ipman.cache.server.server.IMServerPlugin;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * plugins entrypoint.
 *
 * @Author IpMan
 * @Date 2024/6/15 13:30
 */
@Component
public class IMApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    List<IMServerPlugin> plugins;


    @Override
    public void onApplicationEvent(@NonNull ApplicationEvent event) {

        System.out.println("Netty redis起动前, spring线程ID为 " + Thread.currentThread().getId());

        if (event instanceof ApplicationReadyEvent) {
            for (IMServerPlugin plugin : plugins) {
                plugin.init();
                plugin.startup();
            }


            // ---------------- 测试 async --- started to netty server...
            // 测试netty是否异步起动了, 而没有阻塞阻塞spring主线程
            InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
            inheritableThreadLocal.set(String.valueOf(Thread.currentThread().getId()));

            final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleWithFixedDelay(() -> {
                System.out.println("Netty redis起动后, 定时任务进程ID为 "
                        + Thread.currentThread().getId() + ", 父线程ID为 " + inheritableThreadLocal.get());
            }, 2, 2, TimeUnit.SECONDS);


        } else if (event instanceof ContextClosedEvent) {
            for (IMServerPlugin plugin : plugins) {
                plugin.shutdown();
            }
        }


    }
}
