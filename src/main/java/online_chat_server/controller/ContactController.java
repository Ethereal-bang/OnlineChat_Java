package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/add")
    public Result addContact(@RequestParam("id") int id, @RequestParam("contact") int contact) {
        return contactService.add(id, contact)
            ? Result.ok().setMsg("已发送好友申请")
            : Result.err().setMsg("发送好友申请失败");
    }

    @GetMapping("/search/{keyword}")
    public Result search(@PathVariable("keyword") String keyword) {
        return Result.ok().data("list", contactService.search(keyword));
    }

}
