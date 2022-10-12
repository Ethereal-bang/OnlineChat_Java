package online_chat_server.mapper;

import online_chat_server.pojo.ContactItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContactMapper {

    int add(@Param("own") int id, @Param("contact") int contact);

    ContactItem[] searchByName(int id, String keyword);

    ContactItem[] searchByContact(int id, int contact);

    ContactItem[] getAddList(int id);

    boolean changeState(int id, int contact, int state);

    ContactItem[] list(int id);

    int updateNews(int id, String news);

    int updateRead(int id, int contact, boolean bool);

    int delete(int id, int contact);

    ContactItem[] rank(int id);

}
