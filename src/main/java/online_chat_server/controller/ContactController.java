package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.pojo.ContactItem;
import online_chat_server.pojo.User;
import online_chat_server.service.ContactService;
import online_chat_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public Result addContact(@RequestParam("id") int id, @RequestParam("contact") int contact) throws IOException {
        return contactService.add(id, contact)
            ? Result.ok().setMsg("已发送好友申请")
            : Result.err().setMsg("发送好友申请失败");
    }

    @GetMapping("/search/{keyword}")
    public Result search(@RequestParam("id") int id, @PathVariable("keyword") String keyword) {
        // 判断参数属于id还是昵称
        if (keyword.matches("[0-9]+")) {
            // id
            User user = userService.getInfo(Integer.parseInt(keyword));
            ContactItem contactItem = new ContactItem(1, user.getId(),
                    user.getName(), user.getAvatar(), user.getWord(), 0, -1, "", false);
            return Result.ok().data("user", contactItem);
        } else {
            // name 在好友中搜索
            return Result.ok().data("list", contactService.search(id, keyword));
        }
    }

    @GetMapping("/getAddList/{id}")
    public Result getAddList(@PathVariable("id") int id) {
        return Result.ok().data("list", contactService.getAddList(id));
    }

    @GetMapping("/handleApplication")
    public Result handleApplication(
            @RequestParam("id") int id,
            @RequestParam("contact") int contact,
            @RequestParam("state") int state) throws IOException {
        if (contactService.changeState(id, contact, state)) {
            return Result.ok();
        } else {
            return Result.err();
        }
    }

    @GetMapping("/list/{id}")
    public Result list(@PathVariable("id") int id) {
        return Result.ok().data("list", contactService.list(id));
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam("id") int id, @RequestParam("contact") int contact) {
        if (contactService.delete(id, contact)) {
            return Result.ok().setMsg("删除成功");
        } else {
            return Result.err().setMsg("删除失败");
        }
    }

    @GetMapping("/block")
    public Result block(@RequestParam("id") int id, @RequestParam("contact") int contact) throws IOException {
        if (contactService.changeState(id, contact, 3)) {
            return Result.ok().setMsg("已屏蔽");
        } else {
            return Result.err();
        }
    }
}
