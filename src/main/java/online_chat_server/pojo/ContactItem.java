package online_chat_server.pojo;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactItem {
    private int id;
    private int uid;
    private String name;
    private String avatar;
    private String word;
    private int score;
    private int state;
    private String news;
    private boolean read;
}
