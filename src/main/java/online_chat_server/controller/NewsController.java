package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.pojo.News;
import online_chat_server.service.NewsService;
import online_chat_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.EncodeException;
import java.io.IOException;

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
    public Result send(@RequestBody News news) throws IOException, EncodeException {
        if (newsService.send(news)) {
            return Result.ok().data("id", news.getId());
        } else {
            return Result.err();
        }
    }

    @GetMapping("/getDialogue/{page}")
    public Result getDialogue(
            @RequestParam("id") int id,
            @RequestParam("contact") int contact,
            @PathVariable("page") int page
    ) throws IOException {
        return Result.ok()
                .data("user", userService.getInfo(contact))
                .data("list", newsService.getDialogue(id, contact, page));
    }
}
