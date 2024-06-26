package cn.ipman.cache.server.lua;

import cn.ipman.cache.core.core.IMCache;
import org.apache.logging.log4j.util.Strings;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.VarArgFunction;
import org.mockito.internal.util.io.IOUtil;

import java.util.Objects;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/23 19:17
 */
public class RedisLib extends TwoArgFunction {

    protected Globals globals;

    protected IMCache imCache = new IMCache();

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        globals = env.checkglobals();
        LuaTable redis = new LuaTable();
        redis.set("call", new RedisLibFunc());
        env.set("redis", redis);
        env.get("package").get("loaded").set("redis", redis);
        return redis;
    }

    class RedisLibFunc extends VarArgFunction {

        static final String OK = "OK";

        public Varargs invoke(Varargs args) {
            int n = args.narg();
            String cmd = args.arg(1).toString();
            String key = args.arg(2).toString();
            String value = n > 2 ? args.arg(3).toString() : null;

            if ("SET".equalsIgnoreCase(cmd)) {
                imCache.set(key, value);
                return valueOf(OK);
            } else if ("GET".equalsIgnoreCase(cmd)) {
                return valueOf(imCache.get(key));
            } else if ("DEL".equalsIgnoreCase(cmd)) {
                return valueOf(imCache.del(key));
            } else if ("MSET".equalsIgnoreCase(cmd)) {
                for (int i = 2; i < n; i += 2) {
                    imCache.set(args.arg(i).toString(), args.arg(i + 1).toString());
                }
                return valueOf((n - 1) / 2);
            } else if ("EXISTS".equalsIgnoreCase(cmd)) {
                return valueOf(imCache.exists(key));
            }
            return valueOf(OK);
        }
    }

    public static String loadLua(String file) {
        return Strings.join(IOUtil.readLines(Objects.requireNonNull(RedisLib.class.getResourceAsStream(file))), '\n');
    }

    public static void config(Globals globals, String env, String... values) {
        LuaValue[] array = new LuaValue[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = LuaValue.valueOf(values[i]);
        }
        globals.set(env, LuaValue.listOf(array));
    }

    public static void configJava(Globals globals) {
        globals.set("printJava", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                int n = args.narg();
                for (int i = 1; i <= n; i++) {
                    System.out.println(args.arg(i).toString());
                }
                return NIL;
            }
        });
    }

}
