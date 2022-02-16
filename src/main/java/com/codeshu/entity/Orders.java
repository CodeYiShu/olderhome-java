package com.codeshu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author ShuCode
 * @date 2021/12/15 16:43
 * @Email 13828965090@163.com
 */
//订单
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orders {
	private Integer id;
	private String uuid;
	private Integer costId;
}
