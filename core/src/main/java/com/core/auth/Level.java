package com.core.auth;

/**
 *
 * @author: WangHuaQiang
 * @date: 2019年4月12日
 * @description: 安全级别枚举类<br>
 *               目前提供三种级别<br>
 *               ROLE:角色级别<br>
 *               PERMISSION:权限级别<br>
 *               OPERATION:操作（方法）级别
 */
public enum Level {
	/**
	 * @type: {@link Level}
	 * @author: WangHuaQiang
	 * @date: 2019年4月12日
	 * @description: 角色
	 */
	ROLE,
	/**
	 * @type: {@link Level}
	 * @author: WangHuaQiang
	 * @date: 2019年4月12日
	 * @description: 权限
	 */
	PERMISSION,
	/**
	 * @type: {@link Level}
	 * @author: WangHuaQiang
	 * @date: 2019年4月12日
	 * @description: 操作
	 */
	OPERATION
}
