package com.codeshu.service.impl;

import com.codeshu.entity.Admin;
import com.codeshu.mapper.AdminMapper;
import com.codeshu.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
	@Autowired
	private AdminMapper mapper;
	@Override
	public Admin findByName(String username) {
		return mapper.findByName(username);
	}
	@Override
	public int insert(Admin admin) {
		//1、生成随机盐
		String salt = SaltUtils.getSalt(8);
		//2、将随机盐保存到User中
		admin.setSalt(salt);
		//3、将明文密码进行md5+salt+散列进行加密
		Md5Hash md5Hash = new Md5Hash(admin.getPassword(),salt,1024);
		//4、将加密后的密码保存到user中
		admin.setPassword(md5Hash.toHex());
		///5、调用Dao层的saveUser()将user保存到数据库中
		int count = mapper.saveAdmin(admin);
		return count;
	}
}
