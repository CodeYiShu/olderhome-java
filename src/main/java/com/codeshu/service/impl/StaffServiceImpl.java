package com.codeshu.service.impl;

import com.codeshu.entity.Guarder;
import com.codeshu.entity.Staff;
import com.codeshu.mapper.StaffMapper;
import com.codeshu.service.StaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeshu.shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffMapper mapper;
	//根据用户名查询
	@Override
	public Staff findByName(String username) {
		return mapper.selectByName(username);
	}
	//新增
	@Override
	public int save(Staff staff) {  //对密码进行加密后存入
		if(mapper.selectByName(staff.getUsername())!=null){
			return 0;
		}
		//1、生成随机盐
		String salt = SaltUtils.getSalt(8);
		//2、将随机盐保存到staff中
		staff.setSalt(salt);
		//3、将明文密码进行md5+salt+散列进行加密
		Md5Hash md5Hash = new Md5Hash(staff.getPassword(),salt,1024);
		//4、将加密后的密码保存到user中
		staff.setPassword(md5Hash.toHex());
		///5、调用Dao层的saveUser()将user保存到数据库中
		int count = mapper.insert(staff);
		return count;
	}

	@Override
	public int change(Staff staff) {
		return mapper.update(staff);
	}
	@Override
	public Staff changeInfo(Staff staff) {
		//1、重新生成随机盐
		String salt = SaltUtils.getSalt(8);
		//2、将随机盐保存到Admin中
		staff.setSalt(salt);
		//3、重新将明文密码进行md5+salt+散列进行加密
		Md5Hash md5Hash = new Md5Hash(staff.getPassword(),salt,1024);
		//4、将加密后的密码保存到Staff中
		staff.setPassword(md5Hash.toHex());
		//5、调用Dao层的updateInfo()将user保存到数据库中
		mapper.updateInfo(staff);
		//6、再去查询新的监护人员信息
		Staff newStaff = this.findByName(staff.getUsername());
		return newStaff;
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<Staff> findAll() {
		return mapper.selectAll();
	}

	@Override
	public List<Staff> findByLikeName(String likeName) {
		return mapper.selectByLikeName(likeName);
	}

	@Override
	public int remove(Integer id) {
		return mapper.delete(id);
	}

}
