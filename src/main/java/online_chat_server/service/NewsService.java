package online_chat_server.service;

import online_chat_server.common.Result;
import online_chat_server.pojo.News;

import javax.websocket.EncodeException;
import java.io.IOException;

public interface NewsService {

    boolean send(News news) throws IOException, EncodeException;

    News[] getDialogue(int id, int contact, int page) throws IOException;

}
