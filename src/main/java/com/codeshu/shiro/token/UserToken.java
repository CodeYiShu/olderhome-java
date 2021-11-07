package com.codeshu.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author ShuCode
 * @date 2021/11/5 14:26
 * @Email 13828965090@163.com
 */
//类型是UsernamePasswordToken，用于登录
public class UserToken extends UsernamePasswordToken {
	//登录类型，判断是学生登录，教师登录还是管理员登录
	private String loginType;
	public UserToken(final String username, final String password,String loginType) {
		super(username,password);
		this.loginType = loginType;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
}
