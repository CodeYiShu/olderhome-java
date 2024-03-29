package com.codeshu.service.impl;

import com.codeshu.entity.Admin;
import com.codeshu.mapper.AdminMapper;
import com.codeshu.service.AdminService;
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
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper mapper;
	@Override
	public Admin findByName(String username) {
		return mapper.selectByUserName(username);
	}
	@Override
	public int add(Admin admin) {
		if(mapper.selectByUserName(admin.getUsername()) != null){
			return 0;
		}
		//1、生成随机盐
		String salt = SaltUtils.getSalt(8);
		//2、将随机盐保存到Admin中
		admin.setSalt(salt);
		//3、将明文密码进行md5+salt+散列进行加密
		Md5Hash md5Hash = new Md5Hash(admin.getPassword(),salt,1024);
		//4、将加密后的密码保存到Admin中
		admin.setPassword(md5Hash.toHex());
		///5、调用Dao层的saveAdmin()将user保存到数据库中
		int count = mapper.insertAdmin(admin);
		return count;
	}

	@Override
	public Admin changeInfo(Admin admin) {
		//不修改密码
		if (admin.getPassword() == null || admin.getPassword().length() == 0){
			mapper.updateInfo(admin);
		}else {  //修改密码
			//1、重新生成随机盐
			String salt = SaltUtils.getSalt(8);
			//2、将随机盐保存到Admin中
			admin.setSalt(salt);
			//3、重新将明文密码进行md5+salt+散列进行加密
			Md5Hash md5Hash = new Md5Hash(admin.getPassword(), salt, 1024);
			//4、将加密后的密码保存到Admin中
			admin.setPassword(md5Hash.toHex());
			//5、调用Dao层的updateInfo()将user保存到数据库中
			mapper.updateInfoAndPassword(admin);
		}
		//再去查询新的管理员信息返回
		Admin newAdmin = this.findByName(admin.getUsername());
		return newAdmin;
	}
}
