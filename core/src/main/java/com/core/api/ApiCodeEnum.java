package com.core.api;

/**
 * Copyright © 2019 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2019年4月11日
 * @description: 状态枚举类
 */
public enum ApiCodeEnum {

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 成功
	 */
	OK(200, "Ok"),

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 失败
	 */
	FAIL(202, "Fail"),
	
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 请求参数有误
	 */
	BAD_REQUEST(400, "Bad Request"),
	
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 未授权
	 */
	UNAUTHORIZED(401, "Unauthorized"),

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 权限不足
	 */
	FORBIDDEN(403, "Forbidden"),

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description:  未找到满足条件的数据
	 */
	NOT_FOUND(404, "Not Found"),
	
	
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 数据冲突
	 */
	CONFLICT(409, "Conflict"),
	
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 系统异常
	 */
	ERROR(500, "Internal Server Error"),
	
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 未定义
	 */
	UNIMPLEMENTED(501, "Not Implemented"),

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @description: 响应超时
	 */
	TIMEOUT(504, "Gateway Timeout");

	private int value;
	private String message;

	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	ApiCodeEnum(int value, String message) {
		this.value = value;
		this.message = message;
	}
}