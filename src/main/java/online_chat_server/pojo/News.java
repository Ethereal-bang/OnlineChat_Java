package online_chat_server.pojo;

import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class News {
    private int id;
    private int sender;
    private int receiver;
    private String content; // 消息html格式
    private Timestamp time;

    public String getTime() {
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fd.format(time);
    }

}
