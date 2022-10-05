package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.pojo.News;
import online_chat_server.service.NewsService;
import online_chat_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    private NewsService newsService;
    private UserService userService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/send")
    public Result send(@RequestBody News news) {
        if (newsService.send(news)) {
            return Result.ok();
        }
        return Result.err();
    }

    @GetMapping("/getDialogue")
    public Result getDialogue(@RequestParam("id") int id, @RequestParam("contact") int contact) {
        return Result.ok()
                .data("user", userService.getInfo(contact))
                .data("list", newsService.getDialogue(id, contact));
    }
}
