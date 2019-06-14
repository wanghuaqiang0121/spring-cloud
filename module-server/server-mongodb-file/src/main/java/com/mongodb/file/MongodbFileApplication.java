package com.mongodb.file;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class MongodbFileApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication userApplication = new SpringApplication(MongodbFileApplication.class);
    	userApplication.setBannerMode(Banner.Mode.OFF);
    	userApplication.run();
    }
}
