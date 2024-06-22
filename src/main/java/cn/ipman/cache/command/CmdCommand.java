package cn.ipman.cache.command;

import cn.ipman.cache.core.Command;
import cn.ipman.cache.core.IMCache;
import cn.ipman.cache.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 14:16
 */
public class CmdCommand implements Command {

    @Override
    public String name() {
        // *2,$7,COMMAND,$4,DOCS
        return "COMMAND";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        return Reply.string(OK);
    }
}
