package com.github.oneone1995.mvolunteer.config.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wangl on 2017/2/20.
 * springMVC相关配置类
 */
@Configuration
public class MvcWebConfig extends WebMvcConfigurerAdapter {
    //静态资源路径配置
    @Value("${upload.path}")
    private String path;

    /**
     * 静态资源映射配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/img/**").addResourceLocations("file:" + path);
        super.addResourceHandlers(registry);
    }
}
