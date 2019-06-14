package com.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © 2019 All rights reserved.
 *
 * @author: WangHuaQiang
 * @date: 2019年4月11日
 * @description: 顶层实体类
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

	/**
	 * @type: {@link Integer}
	 * @author: Wang.Hua.Qiang
	 * @date: 2018年8月31日
	 * @description: 当前页 默认1 不参与序列化
	 */
	transient Integer page;
	/**
	 * @type: {@link Integer}
	 * @author: Wang.Hua.Qiang
	 * @date: 2018年8月31日
	 * @description: 每页记录数 默认10 不参与序列化
	 */
	transient Integer pageSize;
	/**
	 * @type: {@link List<Sort>}
	 * @author: Wang.Hua.Qiang
	 * @date: 2018年8月31日
	 * @description: 排序list 不参与序列化
	 */
	transient List<Sort> sorts;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if (page != null) {
			this.page = page > 0 ? page : 1;
		} else {
			this.page = 1;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize != null) {
			this.pageSize = pageSize > 0 ? pageSize : 10;
		} else {
			this.pageSize = 1;
		}
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}

	/**
	 *
	 * @author: Wang.Hua.Qiang
	 * @date: 2018年8月31日
	 * @description: 新增校验class
	 */
	public interface Insert {
	};

	/**
	 *
	 * @author: Wang.Hua.Qiang
	 * @date: 2018年8月31日
	 * @description: 修改校验class
	 */
	public interface Update {
	};

	/**
	 *
	 * @author: Wang.Hua.Qiang
	 * @date: 2018年8月31日
	 * @description: 查询列表校验class
	 */
	public interface SelectAll {
	};

	/**
	 *
	 * @author: Wang.Hua.Qiang
	 * @date: 2018年8月31日
	 * @description: 查询单条校验class
	 */
	public interface SelectOne {
	};

	/**
	 *
	 * @author: Wang.Hua.Qiang
	 * @date: 2018年8月31日
	 * @description: 删除校验class
	 */
	public interface Delete {
	};
}
