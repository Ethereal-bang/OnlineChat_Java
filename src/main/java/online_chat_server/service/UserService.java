package online_chat_server.service;

import online_chat_server.pojo.User;

public interface UserService {

    boolean isExist(int id);

    boolean add(int id, String pwd);

    boolean login(int id, String pwd);

    User getInfo(int id);

}
