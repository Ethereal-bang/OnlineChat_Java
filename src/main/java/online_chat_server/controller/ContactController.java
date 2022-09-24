package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.service.ContactService;
import online_chat_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/contact")
public class ContactController {

    ContactService contactService;
    UserService userService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public Result addContact(@RequestParam("id") int id, @RequestParam("contact") int contact) {
        return contactService.add(id, contact)
            ? Result.ok().setMsg("已发送好友申请")
            : Result.err().setMsg("发送好友申请失败");
    }

    @GetMapping("/search/{keyword}")
    public Result search(@RequestParam("id") int id, @PathVariable("keyword") String keyword) {
        // 判断参数属于id还是昵称
        if (keyword.matches("[0-9]+")) {
            // id
            return Result.ok().data("user", userService.getInfo(Integer.parseInt(keyword)));
        } else {
            // name 在好友中搜索
            return Result.ok().data("list", contactService.search(id, keyword));
        }
    }

}
