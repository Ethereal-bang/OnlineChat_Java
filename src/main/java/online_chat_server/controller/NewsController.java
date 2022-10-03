package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.pojo.News;
import online_chat_server.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    private NewsService newsService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/send")
    public Result send(@RequestBody News news) {
        if (newsService.send(news)) {
            return Result.ok();
        }
        return Result.err();
    }

}
