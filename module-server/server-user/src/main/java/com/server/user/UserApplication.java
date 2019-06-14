package com.server.user;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableCaching
/*
 * @EnableScheduling @EnableCaching 这两个注解开启缓存功能
 * 1.@Cacheable 2.@CacheEvict表示删除该缓存数据 3.@CachePut表示修改该缓存数据
 * */
public class UserApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication userApplication = new SpringApplication(UserApplication.class);
    	userApplication.setBannerMode(Banner.Mode.OFF);
    	userApplication.run();
    }
}
