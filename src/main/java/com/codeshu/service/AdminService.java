package com.codeshu.service;

import com.codeshu.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface AdminService{
	Admin findByName(String username);
	/**
	 * 插入一个用户
	 * @param user
	 */
	public int insert(Admin admin);
	Admin changeInfo(Admin admin);
}
