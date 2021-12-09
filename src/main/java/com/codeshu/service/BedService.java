package com.codeshu.service;

import com.codeshu.entity.Bed;
import com.codeshu.entity.Older;

import java.util.List;

/**
 * @author ShuCode
 * @date 2021/12/8 12:51
 * @Email 13828965090@163.com
 */
public interface BedService {
	List<Bed> findAll();
	List<Bed> findByNum(String num);
	Bed findByName(String name);
	int change(Bed bed);
	int remove(Integer id);
	int add(Bed bed);
}
