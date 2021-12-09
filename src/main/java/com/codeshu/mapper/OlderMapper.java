package com.codeshu.mapper;

import com.codeshu.entity.Older;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface OlderMapper{
	List<Older> selectAll();
	List<Older> selectByLikeName(String name);
	List<Older> selectDanger();
	List<Older> selectGuancha();
	Older selectByName(String name);
	int total();
	int update(Older older);
	int delete(Integer id);
	int insert(Older older);
	Older findOlderByGuarderId(Integer guarderId);
}
