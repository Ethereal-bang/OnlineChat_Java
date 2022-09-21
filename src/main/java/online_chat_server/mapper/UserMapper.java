package online_chat_server.mapper;

import online_chat_server.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    boolean isExist(int id);

    boolean add(User user);

    boolean login(User user);

    User getInfo(int id);

}
