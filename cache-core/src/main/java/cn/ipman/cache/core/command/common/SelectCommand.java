package cn.ipman.cache.core.command.common;

import cn.ipman.cache.core.command.Command;
import cn.ipman.cache.core.core.IMCache;
import cn.ipman.cache.core.core.Reply;

/**
 * Description for this class
 *
 * @Author IpMan
 * @Date 2024/6/22 13:27
 */
public class SelectCommand implements Command {

    @Override
    public String name() {
        // SELECT ===>  *2,$6,select,$1,0
        return "SELECT";
    }

    @Override
    public Reply<?> exec(IMCache cache, String[] args) {
        return Reply.string(OK); // 暂不实现select
    }
}
