package com.codeshu.controller;

import com.codeshu.common.Result;
import com.codeshu.entity.Cost;
import com.codeshu.entity.Older;
import com.codeshu.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShuCode
 * @date 2021/12/14 20:59
 * @Email 13828965090@163.com
 */
@RestController
@RequestMapping("/cost")
public class CostController {
	@Autowired
	private CostService costService;
	//根据老人Id查询一个费用
	@GetMapping("/findByOlderId")
	public Result findByOlderById(Integer olderId){
		Cost cost = costService.findByOlderId(olderId);
		return Result.success(olderId);
	}
	@GetMapping("/findAll")
	public Result findAll(){
		return Result.success(costService.findAll());
	}
}
