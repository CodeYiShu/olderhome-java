package com.codeshu.service.impl;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.codeshu.entity.Older;
import com.codeshu.mapper.GuarderMapper;
import com.codeshu.mapper.OlderMapper;
import com.codeshu.service.GuarderService;
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
public class GuarderServiceImpl implements GuarderService {
	@Autowired
	private GuarderMapper guarderMapper;
	@Autowired
	private OlderMapper olderMapper;

	@Override
	public Guarder findByName(String username) {
		return guarderMapper.selectByName(username);
	}

	@Override
	public List<Guarder> findAll() {
		List<Guarder> guarders = guarderMapper.selectAll();
		return guarders;
	}

	@Override
	public List<Guarder> findByLikeName(String likeName) {
		return guarderMapper.selectByLikeName(likeName);
	}

	@Override
	public int add(Guarder guarder) {
		if(guarderMapper.selectByName(guarder.getUsername()) != null){
			return 0;
		}
		//1、生成随机盐
		String salt = SaltUtils.getSalt(8);
		//2、将随机盐保存到User中
		guarder.setSalt(salt);
		//3、将明文密码进行md5+salt+散列进行加密
		Md5Hash md5Hash = new Md5Hash(guarder.getPassword(),salt,1024);
		//4、将加密后的密码保存到user中
		guarder.setPassword(md5Hash.toHex());
		///5、调用Dao层的saveUser()将user保存到数据库中
		int count = guarderMapper.insert(guarder);
		return count;
	}

	/**
	 * 新增一个监护老人
	 * @param olderName
	 * @return
	 */
	@Override
	public int addOlder(String guarderId,String olderName) {
		Older older = olderMapper.selectNotGuarderByName(olderName); //根据老人名称查询没有被监护的老人信息
		if(older == null){  //查询不到则说明老人可能被其他监护人所监护，或者没有此老人
			return 0;
		}
		return guarderMapper.insertOlder(guarderId,olderName);
	}

	@Override
	public int change(Guarder guarder) {
		return guarderMapper.update(guarder);
	}

	@Override
	public Guarder changeInfo(Guarder guarder) {  //修改个人信息
		//不修改密码
		if (guarder.getPassword() == null || guarder.getPassword().length() == 0){
			guarderMapper.update(guarder);
		}else {  //修改密码
			//1、重新生成随机盐
			String salt = SaltUtils.getSalt(8);
			//2、将随机盐保存到Admin中
			guarder.setSalt(salt);
			//3、重新将明文密码进行md5+salt+散列进行加密
			Md5Hash md5Hash = new Md5Hash(guarder.getPassword(), salt, 1024);
			//4、将加密后的密码保存到Guarder中
			guarder.setPassword(md5Hash.toHex());
			//5、调用Dao层的updateInfo()将user保存到数据库中
			guarderMapper.updateInfo(guarder);
		}
		//再去查询新的监护人员信息
		Guarder newGuarder = this.findByName(guarder.getUsername());
		return newGuarder;
	}

	@Override
	public int remove(Integer id) {
		return guarderMapper.delete(id);
	}


}
