package online_chat_server.service;

import online_chat_server.pojo.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String upload(MultipartFile multipartFile);

    boolean add(String name, int uid);

    String[] list(int uid);

}
