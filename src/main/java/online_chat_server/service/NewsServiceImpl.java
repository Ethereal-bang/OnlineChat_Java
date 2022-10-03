package online_chat_server.service;

import online_chat_server.mapper.NewsMapper;
import online_chat_server.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService{

    private NewsMapper newsMapper;

    @Autowired
    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Override
    public boolean send(News news) {
        return newsMapper.add(news) == 1;
    }

    @Override
    public News[] getDialogue(int id) {
        return newsMapper.getDialogue(id);
    }

}
