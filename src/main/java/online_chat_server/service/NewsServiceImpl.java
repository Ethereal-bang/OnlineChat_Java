package online_chat_server.service;

import online_chat_server.mapper.ContactMapper;
import online_chat_server.mapper.NewsMapper;
import online_chat_server.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsMapper newsMapper;
    private ContactMapper contactMapper;

    @Autowired
    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Autowired
    public void setContactMapper(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @Override
    public boolean send(News news) {
        // 消息库新增
        if (newsMapper.add(news) != 1) return false;
        // 将对方已读置为false
        contactMapper.updateRead(news.getSender(), news.getReceiver(), false);
        // 更新联系人最近一次联系
        return contactMapper.updateNews(news.getSender(), news.getWord()) == 2;
    }

    @Override
    public News[] getDialogue(int id, int contact) {
        // 将对方已读置为true
        contactMapper.updateRead(contact, id, true);
        return newsMapper.getDialogue(id, contact);
    }

}
