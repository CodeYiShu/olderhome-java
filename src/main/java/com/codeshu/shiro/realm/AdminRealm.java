package com.codeshu.shiro.realm;

import cn.hutool.core.lang.Assert;
import com.codeshu.entity.Admin;
import com.codeshu.service.AdminService;
import com.codeshu.shiro.token.JwtToken;
import com.codeshu.shiro.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理员登录认证
 * @author ShuCode
 * @date 2021/11/6 10:34
 * @Email 13828965090@163.com
 */
public class AdminRealm extends AuthorizingRealm {

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	AdminService adminService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("管理员认证");
		//获取用户名
		String username = (String)authenticationToken.getPrincipal();
		Admin admin = adminService.findByName(username);
		//抛出异常，到全局异常处理捕获
		if (admin == null){
			throw new UnknownAccountException("管理员不存在");
		}
		//将查询出来的对象，传入第一个参数，保存到当前主体的Principal，我们可以在任何位置调用主体对象的getPrincipal()获取，具体看整合笔记的博客接口的开发
		//将数据库查询出密码和随机盐保存到AuthenticationInfo中
		AuthenticationInfo info = new SimpleAuthenticationInfo(admin,admin.getPassword(),
				ByteSource.Util.bytes(admin.getSalt()),
				this.getName());
		//返回进行密码认证
		return info;
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
}
