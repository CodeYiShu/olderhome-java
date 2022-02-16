package com.codeshu.entity;

import lombok.Data;

/**
 * @author ShuCode
 * @date 2022/2/16 12:02
 * @Email 13828965090@163.com
 */
@Data
public class Health {
	private Integer id;
	private String healthName; //状态名称
	private String description; //描述
}
