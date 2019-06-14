package com.cloud.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	 @Override
    protected void configure(HttpSecurity http) throws Exception {
		// 禁用注册中心密码 
		//http.csrf().disable();
		 
		// 访问路径为 /eureka/** 都需要密码
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
