package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.pojo.User;
import online_chat_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

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

    @GetMapping("/getInfo/{id}")
    public Result getInfo(@PathVariable("id") int id) {
        return Result.ok().data("user", userService.getInfo(id));
    }

    @GetMapping("/modify/{key}")
    public Result modify(@PathVariable("key") String key, @RequestParam("id") int id, @RequestParam("val") String val) {
        boolean flag = false;
        switch (key) {
            case "name":
                flag = userService.setName(id, val);
                break;
            case "word":
                flag = userService.setWord(id, val);
                break;
            case "avatar":
                flag = userService.setAvatar(id, val);
                break;
        }
        return flag
                ? Result.ok().setMsg("修改成功")
                : Result.err().setMsg("修改失败");
    }

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return Result.err().setMsg("文件为空");
        } else {
            String path = userService.upload(multipartFile);
            return !Objects.equals(path, "")
                ? Result.ok().setMsg("上传成功").data("path", path)
                : Result.err().setMsg("上传失败");
        }
    }


}
