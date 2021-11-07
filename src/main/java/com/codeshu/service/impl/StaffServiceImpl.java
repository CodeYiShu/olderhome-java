package com.codeshu.service.impl;

import com.codeshu.entity.Staff;
import com.codeshu.mapper.StaffMapper;
import com.codeshu.service.StaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeshu.shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
	@Autowired
	private StaffMapper mapper;
	@Override
	public Staff findByName(String username) {
		return mapper.findByName(username);
	}
	@Override
	public int insert(Staff staff) {
		//1、生成随机盐
		String salt = SaltUtils.getSalt(8);
		//2、将随机盐保存到User中
		staff.setSalt(salt);
		//3、将明文密码进行md5+salt+散列进行加密
		Md5Hash md5Hash = new Md5Hash(staff.getPassword(),salt,1024);
		//4、将加密后的密码保存到user中
		staff.setPassword(md5Hash.toHex());
		///5、调用Dao层的saveUser()将user保存到数据库中
		int count = mapper.saveStaff(staff);
		return count;
	}
}
