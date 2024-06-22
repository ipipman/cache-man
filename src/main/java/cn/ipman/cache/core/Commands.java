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

        // common
        register(new CmdCommand());
        register(new PingCommand());
        register(new InfoCommand());

        // string
        register(new GetCommand());
        register(new SetCommand());
        register(new StrlenCommand());
        register(new DelCommand());
        register(new ExistsCommand());
        register(new MgetCommand());
        register(new MsetCommand());
        register(new IncrCommand());
        register(new DecrCommand());

        // list
        // Lpush, Rpush, Lpop, Llen, Lindex, Lrange
        register(new LpushCommand());
        register(new LpopCommand());
        register(new RpushCommand());
        register(new RpopCommand());

    }

    public static void register(Command command) {
        ALL.put(command.name(), command);
    }

    public static Command get(String name) {
        return ALL.get(name);
    }


}
