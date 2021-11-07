package com.codeshu.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ShuCode
 * @date 2021/11/6 11:04
 * @Email 13828965090@163.com
 */
//这个token是Jwt
public class JwtToken implements AuthenticationToken {  //为了让Shiro认识JWT，所以自定义一个JwtToken去实现AuthenticationToken接口
	private String token;  //token属性才是真正令牌

	public JwtToken(String token) {  //创建JWTToken对象时，为token赋值
		this.token = token;
	}

	//获取用户名的方法，在这里是获取到字符串的token
	@Override
	public Object getPrincipal() {  //获取用户名的方法，在这里是获取到字符串的token
		return token;
	}

	//获取密码的方法，在这里是获取到字符串的token
	@Override
	public Object getCredentials() {  //获取密码的方法，在这里是获取到字符串的token
		return token;
	}
}
