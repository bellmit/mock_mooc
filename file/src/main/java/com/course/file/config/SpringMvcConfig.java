package com.course.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/f/**")
                .addResourceLocations("file:/home/luoyumo/Desktop/file/imooc/course/");
    }

    // example full path of a teacher avatar
   // http://127.0.0.1:9003/file/f/teacher/9vb6mtjq-avatar2.jpg
}
