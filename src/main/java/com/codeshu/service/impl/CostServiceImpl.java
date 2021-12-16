package com.codeshu.service.impl;

import com.codeshu.entity.Cost;
import com.codeshu.mapper.CostMapper;
import com.codeshu.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShuCode
 * @date 2021/12/14 21:13
 * @Email 13828965090@163.com
 */
@Service
public class CostServiceImpl implements CostService {
	@Autowired
	private CostMapper costMapper;
	@Override
	public Cost findByOlderId(Integer id) {
		return costMapper.selectByOlderId(id);
	}

	@Override
	public List<Cost> findAll() {
		return costMapper.selectAll();
	}
}
