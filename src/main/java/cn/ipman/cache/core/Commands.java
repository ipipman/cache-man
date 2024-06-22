package cn.ipman.cache.core;

import cn.ipman.cache.command.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:07
 */
public class Commands {

    private static Map<String, Command> ALL = new LinkedHashMap<>();

    static {
        initCommands();
    }

    private static void initCommands() {
        register(new PingCommand());
        register(new InfoCommand());
        register(new GetCommand());
        register(new SetCommand());
        register(new StrlenCommand());
        register(new DelCommand());
        register(new ExistsCommand());
        register(new MgetCommand());
        register(new MsetCommand());
    }

    public static void register(Command command) {
        ALL.put(command.name(), command);
    }

    public static Command get(String name) {
        return ALL.get(name);
    }


}
