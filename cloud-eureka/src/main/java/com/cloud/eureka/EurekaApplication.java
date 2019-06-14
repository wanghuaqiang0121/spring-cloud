package com.cloud.eureka;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//启用Eureka服务端
public class EurekaApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(EurekaApplication.class);
		app.setBannerMode(Banner.Mode.OFF);// 关闭springboot启动时的banner图
		app.run(args);
    }
}
