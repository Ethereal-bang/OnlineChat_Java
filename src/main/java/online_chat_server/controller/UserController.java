package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.pojo.User;
import online_chat_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    public String test() {
        return "success";
    }

    @GetMapping("/login")
    public Result login(User user) {
        if (userService.isExist(user.getId())) {
            if (userService.login(user.getId(), user.getPassword())) {
                return Result.ok().setMsg("登录成功");
            } else {
                return Result.err().setMsg("密码错误");
            }
        } else {
            if (userService.add(user.getId(), user.getPassword())) {
                return Result.ok().setMsg("创建成功");
            } else {
                return Result.err().setMsg("创建失败");
            }
        }
    }
}
