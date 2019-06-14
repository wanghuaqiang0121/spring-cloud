package com.core.dao;

import java.util.List;
import java.util.Map;

import com.core.entity.BaseEntity;


/**
 * Copyright © 2019  All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2019年4月11日
 * @param <T>
 * @description: 顶层Mapper
 */
public abstract interface IBaseMapper<T extends BaseEntity> {

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param entity
	 * @return
	 * @description: 新增
	 */
	abstract int insert(T entity);

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param entity
	 * @return
	 * @description: 删除
	 */
	abstract int delete(T entity);

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param entity
	 * @return
	 * @description: 修改
	 */
	abstract int update(T entity);

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param entity
	 * @return
	 * @description: 查询单条
	 */
	abstract Map<String, Object> getOne(T entity);

	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param entity
	 * @return
	 * @description: 查询多条
	 */
	abstract List<Map<String, Object>> getList(T entity);

	
	/** 
	 * @author: WangHuaQiang
	 * @date: 2019年4月11日
	 * @param entity
	 * @return
	 * @description: 检查重复
	 */
	abstract Map<String, Object> getRepeat(T entity);
}