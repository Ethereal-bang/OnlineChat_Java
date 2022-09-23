package online_chat_server.pojo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact {
    private int id;
    private int own;
    private int contact;
    private int score;
    private int state;
}
