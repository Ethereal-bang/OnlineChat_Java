package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    ImageService imageService;

    @Autowired
    public void setImgService(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return Result.err().setMsg("文件为空");
        } else {
            String path = imageService.upload(multipartFile);
            return path.length() > 0
                    ? Result.ok().setMsg("上传成功").data("path", path)
                    : Result.err().setMsg("上传失败");
        }
    }

    @PostMapping("/uploadEmoji/{uid}")
    public Result uploadEmoji(@PathVariable("uid") int uid, @RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return Result.err().setMsg("文件为空");
        } else {
            // 先上传图片到服务器再存入表情包库
            String path = imageService.upload(multipartFile);
            if (imageService.add(path, uid)) {
                return Result.ok().data("path", path);
            }
            return Result.err();
        }
    }

    @GetMapping("/emoji/{uid}")
    public Result list(@PathVariable("uid") int uid) {
        return Result.ok().data("list", imageService.list(uid));
    }

}
