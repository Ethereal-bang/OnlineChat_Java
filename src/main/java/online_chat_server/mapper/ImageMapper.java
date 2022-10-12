package online_chat_server.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    int add(String path, int uid);

}
