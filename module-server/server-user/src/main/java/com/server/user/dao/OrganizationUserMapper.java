package com.server.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.core.dao.IBaseMapper;
import com.server.user.entity.OrganizationUser;

@Mapper
public interface OrganizationUserMapper extends IBaseMapper<OrganizationUser> {

	List<Map<String, Object>> getUserByAccount(OrganizationUser user);

	List<Map<String, Object>> getOrganizationUserModuleList(OrganizationUser user);

	List<Map<String, Object>> getOrganizationUserModuleMenuList(OrganizationUser organizationUser);

	List<Map<String, Object>> getOrganizationUserModuleRoleList(OrganizationUser organizationUser);

	List<Map<String, Object>> getOrganizationUserModulePermissionList(OrganizationUser organizationUser);

	List<Map<String, Object>> getOrganizationUserModuleOperationList(OrganizationUser organizationUser);


	List<Map<String, Object>> getManagerRoleList(OrganizationUser organizationUser);

	List<Map<String, Object>> getManagerModuleMenuList(OrganizationUser organizationUser);

	List<Map<String, Object>> getManagerPermissionList(OrganizationUser organizationUser);

	List<Map<String, Object>> getManagerOperationList(OrganizationUser organizationUser);

	
	List<Map<String, Object>> getOrganzationUserOrganzationList(OrganizationUser organizationUser);

	List<Map<String, Object>> getUserByAccountAndPassword(OrganizationUser user);
	
	Map<String, Object> getDataReturn(OrganizationUser organizationUser);

	Map<String, Object> getOrganizationUserCertificate(OrganizationUser user);

	List<Map<String, Object>> getBoneAgeStandardPercentileList(OrganizationUser user);

}
