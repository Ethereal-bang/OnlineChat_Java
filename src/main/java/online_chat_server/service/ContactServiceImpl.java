package online_chat_server.service;

import online_chat_server.mapper.ContactMapper;
import online_chat_server.mapper.UserMapper;
import online_chat_server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    ContactMapper contactMapper;
    UserMapper userMapper;

    @Autowired
    public void setContactMapper(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean add(int id, int contact) {
        return contactMapper.add(id, contact) == 1;
    }

    @Override
    public User[] search(String keyword) {
        return userMapper.search(keyword);
    }

}