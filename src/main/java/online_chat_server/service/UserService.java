package online_chat_server.service;

import online_chat_server.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    boolean isExist(int id);

    boolean add(int id, String pwd);

    boolean login(int id, String pwd);

    User getInfo(int id);

    boolean setName(String name);

    boolean setWord(String word);

    boolean setAvatar(String avatar);

    String upload(MultipartFile multipartFile);

}
