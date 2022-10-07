package online_chat_server.controller;

import online_chat_server.common.Result;
import online_chat_server.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImgController {

    ImgService imgService;

    @Autowired
    public void setImgService(ImgService imgService) {
        this.imgService = imgService;
    }

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return Result.err().setMsg("文件为空");
        } else {
            String path = imgService.upload(multipartFile);
            return path.length() > 0
                    ? Result.ok().setMsg("上传成功").data("path", path)
                    : Result.err().setMsg("上传失败");
        }
    }

}
