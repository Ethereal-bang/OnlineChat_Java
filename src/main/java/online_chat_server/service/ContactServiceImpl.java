package online_chat_server.service;

import online_chat_server.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    ContactMapper contactMapper;

    @Autowired
    public void setContactMapper(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @Override
    public boolean add(int id, int contact) {
        return contactMapper.add(id, contact) == 1;
    }

}
