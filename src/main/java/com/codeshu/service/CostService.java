package com.codeshu.service;

import com.codeshu.entity.Cost;

import java.util.List;

/**
 * @author ShuCode
 * @date 2021/12/14 21:11
 * @Email 13828965090@163.com
 */
public interface CostService {
	Cost findByOlderId(Integer id);
	List<Cost> findAll();
}
