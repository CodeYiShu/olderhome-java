package com.codeshu.service.impl;

import com.codeshu.entity.Older;
import com.codeshu.mapper.*;
import com.codeshu.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 卡片和图表信息
 * 进行缓存管理
 * @author ShuCode
 * @date 2021/11/28 11:49
 * @Email 13828965090@163.com
 */
@Service
public class InfoServiceImpl implements InfoService {
	private RedisTemplate redisTemplate;
	private OlderMapper olderMapper;
	private StaffMapper staffMapper;
	private AdminMapper adminMapper;
	private GuarderMapper guarderMapper;
	private BedMapper bedMapper;
	public InfoServiceImpl(RedisTemplate redisTemplate,OlderMapper olderMapper,StaffMapper staffMapper,AdminMapper adminMapper,GuarderMapper guarderMapper,BedMapper bedMapper){
		this.redisTemplate = redisTemplate;
		this.olderMapper = olderMapper;
		this.staffMapper = staffMapper;
		this.adminMapper = adminMapper;
		this.guarderMapper = guarderMapper;
		this.bedMapper = bedMapper;
	}

	@Override
	public Map<String, Integer> findCardInfo() {  //得到卡片信息
		//先从缓存中尝试得到key为CardInfo的数据
		Map redisMap = (Map)redisTemplate.opsForValue().get("cardInfo");
		if (redisMap != null){
			return redisMap;
		}
		//如果缓存没有，则从数据库中查询
		Map<String, Integer> map = new  HashMap<>();
		int olderTotal = olderMapper.total();
		List<Older> dangerOlder = olderMapper.selectDanger();
		List<Older> guanchaOlder = olderMapper.selectGuancha();
		int staffTotal = staffMapper.total();
		int guarderTotal = guarderMapper.total();
		int bedTotal = bedMapper.total();
		int bedEmptyTotal = bedMapper.emptyTotal();
		map.put("olderTotal",olderTotal);
		map.put("staffTotal",staffTotal);
		map.put("guarderTotal",guarderTotal);
		map.put("bedTotal",bedTotal);
		map.put("bedEmptyTotal",bedEmptyTotal);
		map.put("dangerOlderTotal",dangerOlder.size());
		map.put("guanchaOlderTotal",guanchaOlder.size());
		//将数据库查询出来的结果，保存到redis中，key为cardInfo，时间为1天
		redisTemplate.opsForValue().set("cardInfo",map,1, TimeUnit.DAYS);
		return map;
	}

	public List<Integer> findEchartsInfo(String year){  //得到图表信息
		//先从缓存中尝试得到key为EchartsInfo的数据
		List<Integer> redisList = (List)redisTemplate.opsForValue().get("echartsInfo_" + year);
		if (redisList != null){
			return redisList;
		}
		//如果缓存没有，则从数据库中查询
		List<Integer> list = new ArrayList<>();
		String j = "1";
		for (int i = 1; i < 12; i++) {  //循环查询12个月
			j = Integer.toString(i);
			if (i < 10){  //拼接成01、02等
				j = "0" + j;
			}
			int count = olderMapper.selectByYearAndMonth(year + "-" + j);  //比如2022-01等
			list.add(count);
		}
		//将数据库查询出来的结果，保存到redis中，key为echartsInfo，时间为1天
		redisTemplate.opsForValue().set("echartsInfo_" + year,list,1, TimeUnit.DAYS);
		return list;
	}
}
