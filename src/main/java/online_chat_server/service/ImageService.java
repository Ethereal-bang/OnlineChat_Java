package online_chat_server.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String upload(MultipartFile multipartFile);

    boolean add(String name, int uid);

}
