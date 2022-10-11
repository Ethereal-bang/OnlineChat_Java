package online_chat_server.service;

import online_chat_server.common.WsNews;
import online_chat_server.mapper.ContactMapper;
import online_chat_server.mapper.UserMapper;
import online_chat_server.pojo.ContactItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ContactServiceImpl implements ContactService {

    ContactMapper contactMapper;
    UserMapper userMapper;
    private WebSocketServer webSocketServer;

    @Autowired
    public void setContactMapper(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setWebSocketServer(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @Override
    public boolean add(int id, int contact) throws IOException {
        webSocketServer.sendMessage(contact, new WsNews("application", "您有新的好友申请"));
        return contactMapper.add(id, contact) == 2;
    }

    @Override
    public ContactItem[] search(int id, String keyword) {
        return contactMapper.searchByName(id, keyword);
    }

    @Override
    public ContactItem[] getAddList(int id) {
        return contactMapper.getAddList(id);
    }

    @Override
    public boolean changeState(int id, int contact, int state) throws IOException {
        // 给对方发送ws(屏蔽不发)
        if (state == 1 || state == 2) {
            String msg = state == 1 ? "您的好友申请已被接受" : "您的好友申请已被拒绝";
            webSocketServer.sendMessage(contact, new WsNews( "application", msg));
        }
        return contactMapper.changeState(id, contact, state);
    }

    @Override
    public ContactItem[] list(int id) {
        return contactMapper.list(id);
    }

    @Override
    public boolean delete(int id, int contact) {
        return contactMapper.delete(id, contact) >= 2;
    }

}
