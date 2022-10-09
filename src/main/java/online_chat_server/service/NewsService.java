package online_chat_server.service;

import online_chat_server.common.Result;
import online_chat_server.pojo.News;

import java.io.IOException;

public interface NewsService {

    boolean send(News news) throws IOException;

    News[] getDialogue(int id, int contact);

}
