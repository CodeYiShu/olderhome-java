package com.codeshu.service;

import com.codeshu.entity.Older;
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
public interface OlderService{
	List<Older> findAll();
	List<Older> findByLikeName(String likeName);
	List<Older> findDangerOlder();
	List<Older> findGuanchaOlder();
	int change(Older older);
	int remove(Integer id);
	int add(Older older);


}
