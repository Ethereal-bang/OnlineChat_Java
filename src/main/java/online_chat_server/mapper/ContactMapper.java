package online_chat_server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContactMapper {

    int add(@Param("own") int id, @Param("contact") int contact);

}
