package com.codeshu.service;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
public interface StaffService{
	Staff findByName(String username);
	List<Staff> findAll();
	List<Staff> findByLikeName(String likeName);
	int save(Staff staff);
	int change(Staff staff);
	int remove(Integer id);
}
