package com.codeshu.controller.common;

import com.codeshu.common.Result;
import com.codeshu.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ShuCode
 * @date 2021/11/28 11:45
 * @Email 13828965090@163.com
 */
@RestController
public class InfoController {
	@Autowired
	private InfoService infoService;
	@GetMapping("/getCardInfo")
	public Result getCardInfo(){  //获取卡片数据
		Map<String,Integer> map = infoService.getCardInfo();
		return Result.success(map);
	}
}
