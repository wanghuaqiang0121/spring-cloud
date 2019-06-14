package com.server.turbine;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TurbineApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication userApplication = new SpringApplication(TurbineApplication.class);
    	userApplication.setBannerMode(Banner.Mode.OFF);
    	userApplication.run();
    }
}
