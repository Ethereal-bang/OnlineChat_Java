package online_chat_server.mapper;

import online_chat_server.pojo.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {

    int add(News news);

    News[] getDialogue(int id);

}
