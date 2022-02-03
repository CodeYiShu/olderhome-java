package com.codeshu.service.impl;

import com.codeshu.entity.Bed;
import com.codeshu.entity.Older;
import com.codeshu.mapper.BedMapper;
import com.codeshu.mapper.OlderMapper;
import com.codeshu.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShuCode
 * @date 2021/12/8 12:51
 * @Email 13828965090@163.com
 */
@Service
public class BedServiceImpl implements BedService {
	@Autowired
	private BedMapper bedMapper;
	@Autowired
	private OlderMapper olderMapper;

	//查询所有
	@Override
	public List<Bed> findAll() {
		List<Bed> bedList = bedMapper.selectAll();
		for (Bed bed : bedList) {
			if("1".equals(bed.getStatus())){
				bed.setStatus("已用");
			}else {
				bed.setStatus("空闲");
			}
		}
		return bedList;
	}
	//根据名称准确查询
	@Override
	public Bed findByName(String name) {
		return bedMapper.selectByName(name);
	}
	//根据床位号模糊查询
	@Override
	public List<Bed> findByNum(String num) {
		List<Bed> bedList = bedMapper.selectByNum(num);
		for (Bed bed : bedList) {
			if("1".equals(bed.getStatus())){
				bed.setStatus("已用");
			}else {
				bed.setStatus("空闲");
			}
		}
		return bedList;
	}


	@Override
	public int change(Bed bed) {
		if("0".equals(bed.getStatus())){ //如果状态为空闲，则直接修改
			return bedMapper.update(bed);
		}
		//状态不为空闲则需要判断老人信息
		Older older = olderMapper.selectByName(bed.getOlderName()); //从数据库查询此老人
		if(older == null){
			return 0;  //如果老人为空则不能修改此床位
		}
		bed.setOlderId(older.getId()); //设置床位所住老人的ID
		//不为空则修改
		return bedMapper.update(bed);
	}

	@Override
	public int remove(Integer id) {
		return bedMapper.delete(id);
	}

	@Override
	public int add(Bed bed) {
		if(bedMapper.selectByName(bed.getName()) != null){  //如果已经有此床位，则不新增
			return 0;
		}
		if("0".equals(bed.getStatus())){ //如果状态为空闲，则直接新增
			return bedMapper.insert(bed);
		}
		//状态不为空闲则需要判断老人信息
		Older older = olderMapper.selectByName(bed.getOlderName()); //从数据库查询此老人
		if(older == null){
			return 0;  //如果老人为空则不能新增此床位
		}
		bed.setOlderId(older.getId()); //设置床位所住老人的ID
		//不为空则新增
		return bedMapper.insert(bed);
	}
}
