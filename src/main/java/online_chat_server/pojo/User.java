package online_chat_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String avatar;
    private String word;
    private String password;

    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }
}
