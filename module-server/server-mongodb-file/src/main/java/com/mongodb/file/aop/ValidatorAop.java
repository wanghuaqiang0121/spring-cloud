package com.mongodb.file.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.core.api.ApiCodeEnum;
import com.core.api.JsonApi;

/**
 *
 * @author: WangHuaQiang
 * @date: 2019年4月12日
 * @description: 切面校验参数
 */
@Component
@Aspect
public class ValidatorAop {

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月12日
	 * @param point
	 * @return
	 * @throws Throwable
	 * @description: 环绕校验
	 */
	@Around("execution(public * com.server.user.controller..*.*(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object[] objects = point.getArgs();
		for (Object object : objects) {
			if (object instanceof BindingResult) {
				if (((BindingResult) object).hasErrors()) {
					return new JsonApi(ApiCodeEnum.BAD_REQUEST)
							.setMsg(((BindingResult) object).getFieldError().getDefaultMessage());
				}
			}
		}
		return point.proceed();
	}
}