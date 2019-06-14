package com.server.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

/*
 *  @FeignClient注解中configuration属性 用于配置这个Feign使用的contract(契约),默认使用springMVC的契约
 *  所以下面才可以使用下面spirngMVC的契约--@PostMapping...*/
@FeignClient(value="${usersecurety}", fallback = IUserSecurityFeignServiceFallback.class)
public interface IUserSecurityFeignService {
	
}
