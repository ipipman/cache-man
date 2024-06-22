package cn.ipman.cache.core;

public interface Command {

    String CRLF = "\r\n";

    String name();

    Reply<?> exec(IMCache cache, String[] args);


}
