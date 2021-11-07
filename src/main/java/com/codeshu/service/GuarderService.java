package com.codeshu.service;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface GuarderService extends IService<Guarder> {
	Guarder findByName(String username);
	/**
	 * 插入一个用户
	 * @param user
	 */
	public int insert(Guarder guarder);
}
