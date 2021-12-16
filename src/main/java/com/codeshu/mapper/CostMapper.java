package com.codeshu.mapper;

import com.codeshu.entity.Cost;
import com.codeshu.entity.Older;

import java.util.List;

/**
 * @author ShuCode
 * @date 2021/12/14 21:00
 * @Email 13828965090@163.com
 */
public interface CostMapper {
	Cost selectByOlderId(Integer id);
	Cost selectById(Integer id);
	List<Cost> selectAll();
	int insert(Cost cost);
	int update(Cost cost);
	int updateByOlderId(Cost cost);
}
