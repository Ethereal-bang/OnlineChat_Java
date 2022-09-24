package online_chat_server.service;

import online_chat_server.pojo.User;

public interface ContactService {

    boolean add(int id, int contact);

    User[] search(String keyword);

}
