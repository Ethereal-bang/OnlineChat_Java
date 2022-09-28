package online_chat_server.service;

import online_chat_server.mapper.ContactMapper;
import online_chat_server.mapper.UserMapper;
import online_chat_server.pojo.ContactItem;
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
        return contactMapper.add(id, contact) == 2;
    }

    @Override
    public ContactItem[] search(int id, String keyword) {
        return contactMapper.search(id, keyword);
    }

    @Override
    public ContactItem[] getAddList(int id) {
        return contactMapper.getAddList(id);
    }

}
