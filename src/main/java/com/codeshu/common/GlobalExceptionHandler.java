package com.codeshu.common;

/**
 * @author ShuCode
 * @date 2021/11/5 14:30
 * @Email 13828965090@163.com
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 日志输出
 * 全局捕获异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.UNAUTHORIZED) //返回一个状态码,一般是401,表示没有权限、token错误等
	@ExceptionHandler(value =  ShiroException.class)//捕获运行时异常ShiroException,他是大部分异常的父类
	public Result handler(ShiroException e){
		log.error("Shiro运行时异常：-----------------{}",e);
		return Result.fail(401,e.getMessage(),null); //用统一结果封装返回
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) //返回一个状态400
	@ExceptionHandler(value =  RuntimeException.class)//捕获运行时异常
	public Result handler(RuntimeException e){
		log.error("运行时异常：-----------------{}",e);
		return Result.fail(e.getMessage());
	}

	/**
	 * 实体校验异常
	 * MethodArgumentNotValidException捕获实体校验异常
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) //返回一个状态400
	@ExceptionHandler(value =  MethodArgumentNotValidException.class)//捕获校验异常
	public Result handler(MethodArgumentNotValidException e){
		log.error("实体捕获异常  ：-----------------{}",e);
		BindingResult bindingException = e.getBindingResult();
		//多个异常顺序抛出异常
		ObjectError objectError = bindingException.getAllErrors().stream().findFirst().get();
		return Result.fail(objectError.getDefaultMessage());
	}

	/**
	 * 断言异常
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)  //400
	@ExceptionHandler(value = IllegalArgumentException.class)
	public Result handler(IllegalArgumentException e){
		log.error("Assert异常:------------------>{}",e);
		return Result.fail(e.getMessage());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED) //返回一个状态码,一般是401,表示没有权限、token错误、密码错误、用户名不存在等
	@ExceptionHandler(value =  AuthenticationException.class)//捕获运行时异常ShiroException,他是大部分异常的父类
	public Result handler(AuthenticationException e){
		if (e instanceof IncorrectCredentialsException){
			log.error("AuthenticationException异常：-----------------{}","密码错误");
			return Result.fail(401,"密码错误",null);
		}
		log.error("AuthenticationException异常：-----------------{}",e.getMessage());
		return Result.fail(401,e.getMessage(),null); //用统一结果封装返回
	}

}
