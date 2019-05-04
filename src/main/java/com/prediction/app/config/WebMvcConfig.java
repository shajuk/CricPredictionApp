package com.prediction.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Shaju K
 *
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder;
	}
}
