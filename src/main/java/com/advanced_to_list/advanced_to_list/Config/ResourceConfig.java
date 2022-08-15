package com.advanced_to_list.advanced_to_list.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        StaticFilesPath staticFilesPath = new StaticFilesPath();
        String path = "file:///" + staticFilesPath.path;
        registry.addResourceHandler("/content/**").addResourceLocations(path);
    }
}
