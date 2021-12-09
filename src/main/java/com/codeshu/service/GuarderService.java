package com.codeshu.service;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codeshu.entity.Staff;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface GuarderService{
	Guarder findByName(String username);
	List<Guarder> findAll();
	List<Guarder> findByLikeName(String likeName);
	int save(Guarder guarder);
	int change(Guarder guarder);
	int remove(Integer id);
}
