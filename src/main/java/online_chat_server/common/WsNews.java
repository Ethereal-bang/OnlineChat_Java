package online_chat_server.common;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class WsNews {

    public int id;
    public String type;
    public String message;

    public WsNews(int id, String type, String message) {
        this.id = id;
        this.type = type;
        this.message = message;
    }

}
