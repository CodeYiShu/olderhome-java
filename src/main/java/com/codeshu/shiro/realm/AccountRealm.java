package com.codeshu.shiro.realm;

import cn.hutool.core.lang.Assert;
import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.codeshu.entity.Staff;
import com.codeshu.service.AdminService;
import com.codeshu.service.GuarderService;
import com.codeshu.service.StaffService;
import com.codeshu.shiro.token.JwtToken;
import com.codeshu.shiro.token.UserToken;
import com.codeshu.shiro.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ShuCode
 * @date 2021/11/5 14:32
 * @Email 13828965090@163.com
 */
@Component
public class AccountRealm extends AuthorizingRealm {
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	AdminService adminService;
	@Autowired
	StaffService staffService;
	@Autowired
	GuarderService guarderService;

	//为了让realm支持JwtToken类型的令牌校验
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 登录认证校验
	 * @param authenticationToken ：调用主体对象login()时传递来的令牌，是JwtToken类型的
	 * @return
	 * @throws AuthenticationException
	 */
	@SneakyThrows
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("我来验证token咯");
		/*如果不是UsernameAndPasswordToken，表示验证JWT*/
		//此时需要给Realm设置一个SimpleCredentialsMatcher密码匹配器而不是上面那个
		this.setCredentialsMatcher(new SimpleCredentialsMatcher());
		//将令牌转为jwtToken类型
		JwtToken jwtToken = (JwtToken) authenticationToken;
		//获取JwtToken中的属性token（本质上是来自于请求头的令牌）
		String token = (String) jwtToken.getPrincipal();

		//传入token，调用工具类进行校验token
		Claims claims = jwtUtils.getClaimByToken(token);
		//从令牌的payload部分获取用户名和角色
		String username = (String) claims.get("username");
		String role = (String) claims.get("role");
		//调用Service，根据用户名获取到用户
 		if("Admin".equals(role)){ //管理员
			Admin admin = adminService.findByName(username);
			// 用户信息  密钥token 当前Realm的名字
			// 第一个参数会存入当前主体对象的Principal，我们可以在任何位置调用主体对象的getPrincipal()获取，具体看整合笔记的博客接口的开发
			// 第二个参数是token，他会在密码匹配器中去和JwtToken中的属性token对比，二者必定相等
			return new SimpleAuthenticationInfo(admin,jwtToken.getCredentials(),getName());
		}else if("Guarder".equals(role)){ //监护人员
			Guarder guarder = guarderService.findByName(username);
			return new SimpleAuthenticationInfo(guarder,jwtToken.getCredentials(),getName());
		}else { //医护人员
			Staff staff = staffService.findByName(username);
			return new SimpleAuthenticationInfo(staff,jwtToken.getCredentials(),getName());
		}
//		//如果用户为空，则表示用户不存在
//		if(user == null){
//			throw new UnknownAccountException("账户不存在");
	}

	//权限校验
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
}
