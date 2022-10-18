package online_chat_server.service;

import online_chat_server.common.WsNews;
import online_chat_server.mapper.ContactMapper;
import online_chat_server.mapper.NewsMapper;
import online_chat_server.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsMapper newsMapper;
    private ContactMapper contactMapper;
    private WebSocketServer webSocketServer;

    @Autowired
    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Autowired
    public void setContactMapper(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @Autowired
    public void setWebSocketServer(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @Override
    public boolean send(News news) throws IOException {
        // 消息库新增
        if (newsMapper.add(news) != 1) return false;
        // 是否被对方屏蔽
        if (contactMapper.searchByContact(news.getReceiver(), news.getSender())[0].getState() == 3) {
            return true;
        } else {
            // 增加亲密度
            contactMapper.addScore(1, news.getSender(), news.getReceiver());
            // 将对方已读置为false
            contactMapper.updateRead(news.getSender(), news.getReceiver(), false);
            // ws提示对方新消息
            news.setTime(new Timestamp(new Date().getTime()));
            webSocketServer.sendMessage(
                    news.getReceiver(),
                    new WsNews( "news", "您有一条新消息")
                            .data("id", news.getSender())
                            .data("news", news)
                            .data("word", news.getContent()));
            // 更新联系人最近一次联系
            return contactMapper.updateNews(news.getSender(), news.getContent(), news.getReceiver()) == 2;
        }
    }

    @Override
    public News[] getDialogue(int id, int contact) {
        // 将对方已读置为true
        contactMapper.updateRead(contact, id, true);
        return newsMapper.getDialogue(id, contact);
    }

}
