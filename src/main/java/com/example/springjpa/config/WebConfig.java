package com.example.springjpa.config;

import com.example.springjpa.utils.FileUtils;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imagesFolderPath = FileUtils.getImagesFolderPath();
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + imagesFolderPath);
    }


}
