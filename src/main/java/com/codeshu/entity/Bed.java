package com.codeshu.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author ShuCode
 * @date 2021/12/8 12:49
 * @Email 13828965090@163.com
 */
@Data
@ToString
public class Bed {
	private Integer id;
	private String name;
	private String status;
	private Integer olderId; //老人Id
	private String olderName; //老人名字
}
