package com.server.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.api.ApiCodeEnum;
import com.core.api.JsonApi;

/**
 *
 * @author: WangHuaQiang
 * @date: 2019年4月25日
 * @description: 全局异常处理器
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public JsonApi defaultErrorHandler(Exception e) {
		if (logger.isErrorEnabled()) {
			logger.error("system appear error msg:{}", e.getMessage());
		}
		return new JsonApi(ApiCodeEnum.ERROR).setMsg(e.getMessage());
	}
}
