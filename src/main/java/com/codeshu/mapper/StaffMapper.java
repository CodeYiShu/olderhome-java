package com.codeshu.mapper;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Staff;
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
public interface StaffMapper {
	Staff selectByName(String username);
	List<Staff> selectAll();
	List<Staff> selectByLikeName(String likeName);
	int total();
	int insert(Staff staff);
	int delete(Integer id);
	int update(Staff staff);
	int updateInfo(Staff staff);
}
