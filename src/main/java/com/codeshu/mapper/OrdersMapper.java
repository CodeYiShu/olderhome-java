package com.codeshu.mapper;

import com.codeshu.entity.Orders;

/**
 * @author ShuCode
 * @date 2021/12/15 16:46
 * @Email 13828965090@163.com
 */
public interface OrdersMapper {
	public int insert(Orders orders);
	public Orders selectByUuid(String uuid);
}
