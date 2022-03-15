package com.bitc.wub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude= {MultipartAutoConfiguration.class})
public class WubApplication {

	public static void main(String[] args) {
		SpringApplication.run(WubApplication.class, args);
	}

}
