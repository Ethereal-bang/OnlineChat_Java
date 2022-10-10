package online_chat_server.service;

import online_chat_server.pojo.ContactItem;

import java.io.IOException;

public interface ContactService {

    boolean add(int id, int contact);

    ContactItem[] search(int id, String keyword);

    ContactItem[] getAddList(int id);

    boolean changeState(int id, int contact, int state) throws IOException;

    ContactItem[] list(int id);
    
}
