package com.mongodb.file.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/**
 *
 * @author: WangHuaQiang
 * @date: 2019年3月25日
 * @description: 连接池配置
 */
@Configuration
public class HikariDataSourceConfiguration {

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年3月25日
	 * @return
	 * @description: 配置数据源
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource hikariDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		return dataSource;
	}
}