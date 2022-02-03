package com.codeshu.service.impl;

import com.codeshu.entity.Cost;
import com.codeshu.entity.Guarder;
import com.codeshu.entity.Older;
import com.codeshu.mapper.CostMapper;
import com.codeshu.mapper.GuarderMapper;
import com.codeshu.mapper.OlderMapper;
import com.codeshu.service.OlderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.template.SimpleDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
@Service
public class OlderServiceImpl implements OlderService {
	@Autowired
	private OlderMapper olderMapper;
	@Autowired
	private GuarderMapper guarderMapper;
	@Autowired
	private CostMapper costMapper;

	@Override
	public List<Older> findAll() { //查询所有
		return olderMapper.selectAll();
	}

	@Override
	public List<Older> findByLikeName(String name) { //模糊查询
		return olderMapper.selectByLikeName(name);
	}

	@Override
	public List<Older> findDangerOlder() {  //紧急
		return olderMapper.selectDanger();
	}

	@Override
	public List<Older> findGuanchaOlder() {  //观察
		return olderMapper.selectGuancha();
	}

	@Override
	public List<Older> findByGuarderName(String guarderName) {
		return olderMapper.selectOlderByGuarderName(guarderName);
	}


	@Override
	public int change(Older older) {
		int count = olderMapper.update(older);  //先更新老人信息
		if (count != 0){ //再更新费用
			Cost cost = older.getCost().setOlderId(older.getId()); //设置缴费的老人ID
			costMapper.updateByOlderId(cost);
		}
		return count;
	}

	@Override
	public int remove(Integer id) {
		return olderMapper.delete(id);
	}

	@Override
	public int add(Older older) {
		Guarder guarder = guarderMapper.selectByName(older.getGuarderName()); //从数据库查询此监护人
		if(guarder == null){
			return 0;  //如果监护人为空则不能新增老人
		}
		older.setGuarderId(guarder.getId());  //设置老人的监护人ID
		olderMapper.insert(older); //插入之后会将自增的老人ID赋值给older对象的id
		Cost cost = older.getCost().setOlderId(older.getId()); //将老人ID设置进缴费中
		return costMapper.insert(cost);
	}


}
