package cn.ipman.cache.config;

import cn.ipman.cache.server.IMServerPlugin;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

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
        if (event instanceof ApplicationReadyEvent readyEvent) {
            for (IMServerPlugin plugin : plugins) {
                plugin.init();
                plugin.startup();
            }
        } else if (event instanceof ContextClosedEvent closedEvent) {
            for (IMServerPlugin plugin : plugins) {
                plugin.shutdown();
            }
        }
    }
}
