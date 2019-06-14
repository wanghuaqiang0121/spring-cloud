package com.server.users.security;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class userSecurityApplication 
{
	@Bean
	@LoadBalanced//整合ribbon,让restTEmplate具有ribbon的负载均衡能力 默认为RoundRobin 轮询
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
    public static void main( String[] args )
    {
        SpringApplication application = new SpringApplication(userSecurityApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run();
    }
}
