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
 * @author ShuCode
 * @date 2021/11/6 10:34
 * @Email 13828965090@163.com
 */
public class GuarderRealm extends AuthorizingRealm {

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	GuarderService guarderService;

	//为了让realm支持JwtToken类型的令牌校验
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken || token instanceof UsernamePasswordToken;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("监护人员认证");
		//获取用户名
		String username = (String)authenticationToken.getPrincipal();
		Guarder guarder = guarderService.findByName(username);
		if(guarder != null){
			//将数据库查询出的用户名、密码和随机盐保存到AuthenticationInfo中
			AuthenticationInfo info = new SimpleAuthenticationInfo(guarder.getUsername(),guarder.getPassword(),
					ByteSource.Util.bytes(guarder.getSalt()),
					this.getName());
			//返回进行密码认证
			return info;
		}else {
			throw new AuthenticationException("该用户不存在！");
		}
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
}
