package online_chat_server.service;

public interface UserService {

    boolean isExist(int id);

    boolean add(int id, String pwd);

    boolean login(int id, String pwd);

}
