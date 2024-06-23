package cn.ipman.cache.command;

import cn.ipman.cache.command.common.*;
import cn.ipman.cache.command.hash.*;
import cn.ipman.cache.command.list.*;
import cn.ipman.cache.command.set.*;
import cn.ipman.cache.command.string.*;
import cn.ipman.cache.command.zset.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:07
 */
public class Commands {

    private static final Map<String, Command> ALL = new LinkedHashMap<>();

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
        register(new LlenCommand());
        register(new LindexCommand());
        register(new LrangeCommand());

        // set
        register(new SaddCommand());
        register(new SmembersCommand());
        register(new SremCommand());
        register(new SaddCommand());
        register(new ScardCommand());
        register(new SpopCommand());
        register(new SismemberCommand());

        // hash: hset hget hlen hgetall hdel hexists hmget
        register(new HsetCommand());
        register(new HgetCommand());
        register(new HgetallCommand());
        register(new HlenCommand());
        register(new HdelCommand());
        register(new HmgetCommand());
        register(new HexistsCommand());

        // zset
        register(new ZaddCommand());
        register(new ZcardCommand());
        register(new ZcountCommand());
        register(new ZrankCommand());
        register(new ZremCommand());
        register(new ZscoreCommand());

    }

    public static void register(Command command) {
        ALL.put(command.name(), command);
    }

    public static Command get(String name) {
        return ALL.get(name);
    }


}
