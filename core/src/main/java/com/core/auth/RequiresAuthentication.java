package com.core.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author: WangHuaQiang
 * @date: 2019年4月12日
 * @description: 安全控制注解
 */

/*@Retention 在注解类上使用另一个注解类，那么被使用的注解类就称为元注解
 * 注解的生命周期 RetentionPolicy.RUNTIME 程序运行时都存在*/ 
@Retention(RetentionPolicy.RUNTIME)
/*Target注解决定MyAnnotation注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上等成分*/
@Target(ElementType.METHOD)
public @interface RequiresAuthentication {

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月12日
	 * @return
	 * @description: 控制细粒度（级别) 默认OPERATION(操作)
	 */
	Level level() default Level.OPERATION;

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月12日
	 * @return
	 * @description: 权限描述值，用于匹配数据库 默认为空<br>
	 *               建议命名规则：权限等级[:模块][:对象][:操作]<br>
	 *               例如：<br>
	 *               role:user:manager 用户管理角色<br>
	 *               perms:user:user:select 用户查询权限<br>
	 *               op:user:user:user:insert 新增用户操作<br>
	 */
	String[] value() default { "" };

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月12日
	 * @return
	 * @description: 是否忽略校验 默认false<br>
	 *               设置为true时拦截器应该直接放行 适用于登录 注册 找回密码等不需要任何权限控制的接口<br>
	 *               设置为false时拦截器应该进入登录鉴权控制<br>
	 */
	boolean ignore() default false;

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月12日
	 * @return
	 * @description: 是否只做登录鉴权 默认false
	 *               设置为true时检测到用户登录未过期即可放行<br>
	 *               设置为false时必须是用户已登录状态才进入动态权限判断控制<br>
	 */
	boolean authc() default false;
}
