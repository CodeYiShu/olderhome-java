package com.codeshu.shiro.utils;

import java.util.Random;

/**
 * @author ShuCode
 * @date 2021/11/5 15:40
 * @Email 13828965090@163.com
 */
public class SaltUtils {
	public static String getSalt(int n){  //n表示随机盐的位数
		//随机盐字符
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwyz0123456789!@#$%^&*()".toCharArray();
		//用于存放随机盐结果
		StringBuffer salt = new StringBuffer();
		//遍历获取n个随机盐字符
		for(int i = 0; i < n; i++){
			//从chars中获取随机的字符
			char c = chars[new Random().nextInt(chars.length)];
			//将随机获取到字符放入salt中
			salt.append(c);
		}
		//将结果随机盐返回
		return salt.toString();
	}
}
