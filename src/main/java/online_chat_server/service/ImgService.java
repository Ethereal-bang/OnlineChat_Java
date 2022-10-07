package online_chat_server.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImgService {

    String upload(MultipartFile multipartFile);

}
