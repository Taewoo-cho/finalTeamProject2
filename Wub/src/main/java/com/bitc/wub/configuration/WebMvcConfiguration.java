package com.bitc.wub.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		
		// 최대 파일 크기 5mb
		commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
		
		return commonsMultipartResolver;
	}
	
	// 외부 이미지 경로
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/img/**").addResourceLocations("file:///C:/WakeUpBooks/img/");
		//registry.addResourceHandler("/WakeUpBooks/img/**").addResourceLocations("file:///C:/WakeUpBooks/img/");
		// aws 서버용
		registry.addResourceHandler("/img/**").addResourceLocations("file:/home/ec2-user/WakeUpBooks/img/");
		registry.addResourceHandler("/WakeUpBooks/img/**").addResourceLocations("file:/home/ec2-user/WakeUpBooks/img/");

	}
}
