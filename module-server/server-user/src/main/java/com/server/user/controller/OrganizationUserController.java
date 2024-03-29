package com.server.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.core.api.ApiCodeEnum;
import com.core.api.JsonApi;
import com.core.api.MultiLine;
import com.core.entity.BaseEntity.SelectAll;
import com.core.entity.BaseEntity.Update;
import com.core.md5.MD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.server.user.config.RedisUtils;
import com.server.user.entity.OrganizationUser;
import com.server.user.global.BaseGlobal;
import com.server.user.global.IOrganization.OrganizationStatus;
import com.server.user.message.Prompt;
import com.server.user.service.OrganizationUserService;

/**
 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
 * @date 2018年1月8日
 * @version 1.0
 * @description 机构用户
 */
@RestController
public class OrganizationUserController {

	@Autowired
	private OrganizationUserService organizationUserService;
	@Autowired
	private RedisUtils redisUtil;
	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @param user
	 * @param result
	 * @return {@link JsonApi}
	 * @date 2018年3月8日
	 * @version 1.0
	 * @throws InterruptedException 
	 * @description 机构用户登录
	 */
	//@RequiresAuthentication(ignore = true, value = { "organization:user:organizationUserLogin" })
	@RequestMapping(value = { "/organizationUser/login" }, method = RequestMethod.GET)
	public JsonApi organizationUserLogin(@Validated({ OrganizationUser.Login.class }) OrganizationUser user,
			BindingResult result) throws InterruptedException {
		Thread.sleep(100000);
		String password = user.getPassword();
		user.setPassword(MD5Util.getInstance().getMD5Code(password));
		// 查询账号是否存在
		List<Map<String, Object>> userMap = organizationUserService.getUserByAccount(user);
		// 账号不存在
		if (userMap == null || userMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.account.is.not.exists"));
		}
		else if (userMap != null && !userMap.isEmpty()) {// 账号存在
			List<Map<String, Object>> userList = organizationUserService.getUserByAccountAndPassword(user);
			/* 测试@Cacheable*/
			/*user.setPercentile("three_percentile");
			user.setSex(1);
			user.setType("R");
			Map<String, Object> tw3Map = organizationUserService.getTw3StandardPercentiles(user);*/
			
			if (userList == null || userList.isEmpty()) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.password.is.not.exists"));
			}
			/* 如果查询出来的userList中organizationStatus 全都被禁用 不允许用户登录*/
			List<Map<String, Object>> organizationStatusList = userList.stream().filter(map -> (Integer)map.get("organizationStatus")== OrganizationStatus.ENABLE.getValue()).collect(Collectors.toList());
			if (organizationStatusList == null || organizationStatusList.isEmpty()) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.status.is.disabled"));
			}
			/* 如果查询出来的userList中bdoddStatus（关联表状态） 全都被禁用 不允许用户登录*/
			List<Map<String, Object>> bdoddStatusList = userList.stream().filter(map -> (Integer)map.get("bdoddStatus")== OrganizationStatus.ENABLE.getValue()).collect(Collectors.toList());
			if (bdoddStatusList == null || bdoddStatusList.isEmpty()) {
				// 判断用户是否启用
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.status.is.disabled"));
			}
			// 登录成功
			// 存入缓存  1 代表平台
			String token = MD5Util.getInstance().getSessionToken(userList.get(0).get("id"));
			/* 取一条数据去除机构相关信息 */
			Map<String, Object> userResultMap = userList.get(0);
			Integer organizationUserId = (Integer) userResultMap.get("id");
			user.setId(organizationUserId);
			user.setCertificateType("1");
			Map<String, Object> organizationUserCertificateMap = organizationUserService.getOrganizationUserCertificate(user);
			
			userResultMap.put("certificateNumber", organizationUserCertificateMap != null ? organizationUserCertificateMap.get("certificateNumber") : null);
			userResultMap.put("positive", organizationUserCertificateMap != null ? organizationUserCertificateMap.get("positive") : null);
			userResultMap.put("opposite", organizationUserCertificateMap != null ? organizationUserCertificateMap.get("opposite") : null);
			userResultMap.remove("bdoddStatus");
			
			userResultMap.put("token", token);
			// 放入缓存 已实现自动踢出  过期时间30分钟
			redisUtil.insertOrganizationUserLoginCache(token, userResultMap, 30*60);
			/*
			 * 获取缓存测试
			@SuppressWarnings("unchecked")
			Map<String, Object> cacheMap = (Map<String, Object>) redisUtil.getOrganizationUserLoginCache(token);*/
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("info", userResultMap);
			resultMap.put("token", token);
			// 返回用户信息
			return new JsonApi(ApiCodeEnum.OK, resultMap);
		}
		return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.login.account.error"));
	}

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @return {@link JsonApi}
	 * @date 2018年3月12日
	 * @version 1.0
	 * @description 修改用户信息
	 */
	//@RequiresAuthentication(authc = true, value = { "organization:user:update" })
	@RequestMapping(value = { "/organizationUser/update" }, method = RequestMethod.PUT)
	public JsonApi updateOrganizationUser(@Validated({ Update.class }) @RequestBody OrganizationUser organizationUser,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		// 查询用户缓存数据
		//AuthenticationSession session = cacheManager.getSession(new AuthenticationToken(BaseGlobal.CACHE_ORGANIZATION_USER, token));
		//organizationUser.setId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		if (organizationUserService.update(organizationUser) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @param organizationId
	 * @param moduleId
	 * @return
	 * @date 2018年4月18日
	 * @version 1.0
	 * @description 查询机构用户的机构列表
	 */
	//@RequiresAuthentication(authc = true, value = { "organization:user:organizations" })
	@RequestMapping(value = { "/organizationUser/organizations" }, method = RequestMethod.GET)
	public JsonApi getOrganzationUserOrganzationList(
			@Validated({ SelectAll.class }) OrganizationUser organizationUser,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {		
		// 获取缓存信息
		//AuthenticationSession session = cacheManager.getSession(new AuthenticationToken(BaseGlobal.CACHE_ORGANIZATION_USER, token));
		//organizationUser.setId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
		List<Map<String, Object>> organizationList = organizationUserService.getOrganzationUserOrganzationList(organizationUser);
		if (organizationList != null && !organizationList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
}
