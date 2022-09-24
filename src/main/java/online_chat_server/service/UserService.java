package online_chat_server.service;

import online_chat_server.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    boolean isExist(int id);

    boolean add(int id, String pwd);

    boolean login(int id, String pwd);

    User getInfo(int id);

    boolean setName(int id, String name);

    boolean setWord(int id, String word);

    boolean setAvatar(int id, String avatar);

    String upload(MultipartFile multipartFile);

}
