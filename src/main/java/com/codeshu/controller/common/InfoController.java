package com.codeshu.controller.common;

import com.codeshu.common.Result;
import com.codeshu.entity.Older;
import com.codeshu.service.InfoService;
import com.codeshu.service.OlderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
	@Autowired
	private OlderService olderService;

	@GetMapping("/getCardInfo")
	public Result getCardInfo(){  //获取卡片数据
		Map<String,Integer> map = infoService.getCardInfo();
		return Result.success(map);
	}

	@GetMapping("/getEchartsInfo")
	public Result getEchartsInfo(String year){  //根据年份得到老人新增数量
		List<Integer> echartsInfo = infoService.getEchartsInfo(year);
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String format = simpleDateFormat.format(older.getEnterDate());
//		olderService.add(older);
		return Result.success(echartsInfo);
	}
}
