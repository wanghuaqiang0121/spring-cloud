package com.server.users.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.server.users.security.entity.User;
import com.server.users.security.feign.IUserFeignService;

@RestController
public class SecurityController {

	@Autowired
	private IUserFeignService userFeignService;
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("securety/get/user/by/ribbon")
	@SuppressWarnings("unchecked")
	public Map<String, Object> getUser(User user){
		return  (Map<String, Object>) this.restTemplate.getForObject("http://server-user/user/login", Object.class);
	}
	
	@GetMapping("securety/add/user/by/feign")
	public User add(@RequestBody User user){
		return  userFeignService.getUser(user);
	}
	@GetMapping("get/roles")
	public User getRoles(@RequestBody User user){
		return  user;
	}
	
	
}
