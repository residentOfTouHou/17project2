/**
 *
 */
package com.cskaoyan.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourseConfigAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\";
        registry.addResourceHandler("/**").addResourceLocations("file:"+path);
        super.addResourceHandlers(registry);
    }
}
