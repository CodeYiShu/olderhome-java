package com.codeshu.mapper;

import com.codeshu.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface AdminMapper extends BaseMapper<Admin> {
	public Admin findByName(String username);
	/**
	 * 保存一个用户
	 * @param user
	 */
	public int saveAdmin(Admin user);
}
