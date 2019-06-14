package com.server.users.security.feign;

import org.springframework.stereotype.Component;

import com.server.users.security.entity.User;
@Component
public class IUserFeignServiceFallback implements IUserFeignService {

	@Override
	public User getUser(User user) {
		user.setName("访问超时");
		return user;
	}

}
