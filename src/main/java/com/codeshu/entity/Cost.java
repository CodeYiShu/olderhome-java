package com.codeshu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author ShuCode
 * @date 2021/12/14 20:57
 * @Email 13828965090@163.com
 */
//缴费情况
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Cost {
	private Integer id;
	private Integer total;
	private String status;
	private Integer olderId;
}
