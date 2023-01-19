package online_chat_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    // 跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedOrigins("*");
    }

    // 自定义资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String filePath = "/www/pictures/online_chat/";
        WebMvcConfigurer.super.addResourceHandlers(registry);
        // url: localhost:25639/images/图片.png
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .addResourceLocations("file:" + filePath);
    }
}
