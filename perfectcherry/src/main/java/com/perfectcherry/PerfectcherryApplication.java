package com.perfectcherry;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.perfectcherry.custom.properties.ImageStorageProperties;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
@EnableConfigurationProperties({ ImageStorageProperties.class })
public class PerfectcherryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfectcherryApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
