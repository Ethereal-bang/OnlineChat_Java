package online_chat_server.service;

import online_chat_server.common.Result;
import online_chat_server.pojo.News;

public interface NewsService {

    boolean send(News news);

    News[] getDialogue(int id, int contact);

}
