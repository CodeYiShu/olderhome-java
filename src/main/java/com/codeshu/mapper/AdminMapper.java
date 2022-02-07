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
public interface AdminMapper{
	public Admin findByName(String username);
	public Admin selectById(Integer id);
	/**
	 * 保存一个用户
	 * @param user
	 */
	public int saveAdmin(Admin user);
	int updateInfo(Admin admin);  //不修改密码
	int updateInfoAndPassword(Admin admin); //修改密码
}
