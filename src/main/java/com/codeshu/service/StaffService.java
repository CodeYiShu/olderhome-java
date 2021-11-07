package com.codeshu.service;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface StaffService extends IService<Staff> {
	Staff findByName(String username);

	public int insert(Staff staff);
}
