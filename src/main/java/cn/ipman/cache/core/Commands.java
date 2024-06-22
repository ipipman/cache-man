package cn.ipman.cache.core;

import cn.ipman.cache.command.InfoCommand;
import cn.ipman.cache.command.PingCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:07
 */
public class Commands {

    private static Map<String, Command> ALL = new HashMap<>();

    static {
        initCommands();
    }

    private static void initCommands() {
        register(new PingCommand());
        register(new InfoCommand());
    }

    public static void register(Command command) {
        ALL.put(command.name(), command);
    }

    public static Command get(String name) {
        return ALL.get(name);
    }


}
