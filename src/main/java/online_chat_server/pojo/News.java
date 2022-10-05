package online_chat_server.pojo;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Date time;

    public String getTime() {
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return fd.format(time);
    }

}
