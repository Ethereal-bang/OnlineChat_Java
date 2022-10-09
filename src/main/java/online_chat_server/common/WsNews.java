package online_chat_server.common;

import lombok.Data;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Setter
public class WsNews {

    public String type;
    public String message;
    private Map<String, Object> data = new HashMap<>();

    public WsNews(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public WsNews data(String key, Object val) {
        this.data.put(key, val);
        return this;
    }

}
