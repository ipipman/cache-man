package cn.ipman.cache.core;

public interface Command {

    String name();

    Reply<?> exec(IMCache cache, String[] args);


}
