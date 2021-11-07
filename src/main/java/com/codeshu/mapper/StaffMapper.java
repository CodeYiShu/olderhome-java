package com.codeshu.mapper;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface StaffMapper extends BaseMapper<Staff> {
	public Staff findByName(String username);
	public int saveStaff(Staff staff);
}
