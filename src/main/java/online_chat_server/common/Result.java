package online_chat_server.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Result {
    private boolean flag;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    private Result() {}

    public static Result ok() {
        Result res  = new Result();
        res.setFlag(true);
        res.setMsg("查询成功");
        return res;
    }
    public static Result err() {
        Result res  = new Result();
        res.setFlag(false);
        res.setMsg("查询失败");
        return res;
    }
    public Result data(String key, Object val) {
        this.data.put(key, val);
        return this;
    }
    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
