package online_chat_server.mapper;

import online_chat_server.pojo.Image;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    int add(String path, int uid);

    String[] list(int uid);

}
