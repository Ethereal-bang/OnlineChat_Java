package online_chat_server.mapper;

import online_chat_server.pojo.ContactItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContactMapper {

    int add(@Param("own") int id, @Param("contact") int contact);

    ContactItem[] search(int id, String keyword);

    ContactItem[] getAddList(int id);

}
