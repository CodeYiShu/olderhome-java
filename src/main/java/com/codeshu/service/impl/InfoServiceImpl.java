package com.codeshu.service.impl;

import com.codeshu.entity.Older;
import com.codeshu.mapper.*;
import com.codeshu.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuCode
 * @date 2021/11/28 11:49
 * @Email 13828965090@163.com
 */
//获取信息
@Service
public class InfoServiceImpl implements InfoService {
	private OlderMapper olderMapper;
	private StaffMapper staffMapper;
	private AdminMapper adminMapper;
	private GuarderMapper guarderMapper;
	private BedMapper bedMapper;
	public InfoServiceImpl(OlderMapper olderMapper,StaffMapper staffMapper,AdminMapper adminMapper,GuarderMapper guarderMapper,BedMapper bedMapper){
		this.olderMapper = olderMapper;
		this.staffMapper = staffMapper;
		this.adminMapper = adminMapper;
		this.guarderMapper = guarderMapper;
		this.bedMapper = bedMapper;
	}

	@Override
	public Map<String, Integer> getCardInfo() {
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
		return map;
	}
}
