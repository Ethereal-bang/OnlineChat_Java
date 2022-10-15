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
    private String content; // markdown
    private String word;    // 纯文本
    private Timestamp time;

    public String getTime() {
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fd.format(time);
    }

}
