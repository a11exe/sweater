package com.alllexe.sweater.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 06.12.2019
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Value("${upload.path}")
  String uploadPath;

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/img/**")
        .addResourceLocations("file:///" + uploadPath + "/");
    registry.addResourceHandler("static/**")
        .addResourceLocations("classpath:/static/");
  }
}
