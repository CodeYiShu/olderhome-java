package com.codeshu.mapper;

import com.codeshu.entity.Health;

import java.util.List;

/**
 * @author ShuCode
 * @date 2022/2/16 12:09
 * @Email 13828965090@163.com
 */
public interface HealthMapper {
	List<Health> selectAll();
	Health selectById(Integer id);
}
