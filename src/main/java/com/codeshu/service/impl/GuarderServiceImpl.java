package com.codeshu.service.impl;

import com.codeshu.entity.Guarder;
import com.codeshu.entity.Older;
import com.codeshu.mapper.GuarderMapper;
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
	private GuarderMapper mapper;
	@Override
	public Guarder findByName(String username) {
		return mapper.selectByName(username);
	}

	@Override
	public List<Guarder> findAll() {
		List<Guarder> guarders = mapper.selectAll();
		return guarders;
	}

	@Override
	public List<Guarder> findByLikeName(String likeName) {
		return mapper.selectByLikeName(likeName);
	}

	@Override
	public int save(Guarder guarder) {
		if(mapper.selectByName(guarder.getUsername()) != null){
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
		int count = mapper.insert(guarder);
		return count;
	}

	@Override
	public int change(Guarder guarder) {
		return mapper.update(guarder);
	}

	@Override
	public int remove(Integer id) {
		return mapper.delete(id);
	}


}
