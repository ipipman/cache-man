package cn.ipman.cache.core;

public interface Command {

    String CRLF = "\r\n";

    String OK = "OK";

    String name();

    Reply<?> exec(IMCache cache, String[] args);

    // add default args operator
    default String getKey(String[] args) {
        return args[4];
    }

    default String getValue(String[] args) {
        return args[6];
    }

    default String[] getKeys(String[] args) {
        int len = (args.length - 3) / 2;
        String[] keys = new String[len];
        for (int i = 0; i < len; i++) {
            keys[i] = args[4 + i * 2];
        }
        return keys;
    }
}
