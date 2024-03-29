package com.codeshu.shiro.realm;

import com.codeshu.entity.Guarder;
import com.codeshu.service.GuarderService;
import com.codeshu.shiro.token.JwtToken;
import com.codeshu.shiro.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 监护人员登录认证
 * @author ShuCode
 * @date 2021/11/6 10:34
 * @Email 13828965090@163.com
 */
public class GuarderRealm extends AuthorizingRealm {

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	GuarderService guarderService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("监护人员认证");
		//获取用户名
		String username = (String)authenticationToken.getPrincipal();
		Guarder guarder = guarderService.findByName(username);
		//抛出异常，到全局异常处理捕获
		if (guarder == null){
			throw new UnknownAccountException("监护人员不存在");
		}
		//将查询出来的对象，传入第一个参数，保存到当前主体的Principal
		//将数据库查询出的密码和随机盐保存到AuthenticationInfo中
		AuthenticationInfo info = new SimpleAuthenticationInfo(guarder,guarder.getPassword(),
				ByteSource.Util.bytes(guarder.getSalt()),
				this.getName());
		//返回进行密码认证
		return info;
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
}
