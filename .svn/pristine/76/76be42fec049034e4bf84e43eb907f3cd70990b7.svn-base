package com.server.users.security.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.server.users.security.entity.User;

// @FeignClient注解中configuration属性 用于配置这个Feign使用的contract(契约),默认使用springMVC的契约
// 所以下面才可以使用下面spirngMVC的契约--@PostMapping...
@FeignClient(value ="${userfeign}",fallback = IUserFeignServiceFallback.class)
public interface IUserFeignService {

	// 必须设置consumes = "application/json" 才可以传对象参数
	// spring cloud 低版本不支持@PostMapping这种复合注解
	@PostMapping(value="add/user",consumes = "application/json")
	public User getUser(@RequestBody User user);
}
