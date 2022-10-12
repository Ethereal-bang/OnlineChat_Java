package online_chat_server.service;

import online_chat_server.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageMapper imageMapper;

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        String filesDir = "E:\\Pictures\\server\\";   // 放置图片的文件夹
        // 获取源文件名称
        String originalFilename = multipartFile.getOriginalFilename();
        // 生成不重复标识
        UUID uuid = UUID.randomUUID();
        // 获取源文件后缀
        assert originalFilename != null;
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        // 保存文件
        File file = new File(filesDir + uuid + fileSuffix);

        try {
            multipartFile.transferTo(file);
            return "http://localhost:8080/images/" + uuid + fileSuffix; // 返回图片完整访问路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean add(String path, int uid) {
        return imageMapper.add(path, uid) == 1;
    }

}
