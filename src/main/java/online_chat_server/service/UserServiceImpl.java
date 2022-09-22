package online_chat_server.service;

import online_chat_server.mapper.UserMapper;
import online_chat_server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    @Override
    public User getInfo(int id) {
        return userMapper.getInfo(id);
    }

    @Override
    public boolean setName(String name) {
        return userMapper.setName(name);
    }

    @Override
    public boolean setWord(String word) {
        return userMapper.setWord(word);
    }

    @Override
    public boolean setAvatar(String avatar) {
        return userMapper.setAvatar(avatar);
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        String filesDir = "E:\\Pictures\\server\\";   // 放置图片的文件夹
        String filename = multipartFile.getOriginalFilename();
        filename = filename + (int) (Math.random() * 10000) + ".png";    // 加上random戳，防止重名
        File file = new File(filesDir + filename);
        if (!file.getParentFile().exists()) {   // 文件夹不存在则创建
            file.getParentFile().mkdirs();
        }

        try {
            multipartFile.transferTo(file);
            return file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
