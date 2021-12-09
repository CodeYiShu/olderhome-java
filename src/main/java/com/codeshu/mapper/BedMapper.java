package com.codeshu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeshu.entity.Admin;
import com.codeshu.entity.Bed;
import com.codeshu.entity.Older;

import java.util.List;

/**
 * @author ShuCode
 * @date 2021/12/8 12:50
 * @Email 13828965090@163.com
 */

public interface BedMapper{
	int total();
	int emptyTotal();
	List<Bed> selectAll();
	List<Bed> selectByNum(String num);
	Bed selectByName(String name);
	int update(Bed bed);
	int delete(Integer id);
	int insert(Bed bed);

}
