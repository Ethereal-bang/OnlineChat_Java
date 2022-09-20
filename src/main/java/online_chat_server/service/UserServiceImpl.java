package online_chat_server.service;

import online_chat_server.mapper.UserMapper;
import online_chat_server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean isExist(int id) {
        return userMapper.isExist(id);
    }

    @Override
    public boolean add(int id, String pwd) {
        return userMapper.add(new User(id, pwd));
    }

    @Override
    public boolean login(int id, String pwd) {
        return userMapper.login(new User(id, pwd));
    }

}
