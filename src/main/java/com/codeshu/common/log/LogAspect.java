package com.codeshu.common.log;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ShuCode
 * @date 2022/1/28 21:34
 * @Email 13828965090@163.com
 */
@Component
@Aspect  //切面类
@Slf4j
public class LogAspect {
	//切点
//	@Pointcut(value = "@annotation(com.codeshu.common.log.LogAnnotation)")
	@Pointcut(value = "execution(* com.codeshu.controller.*.*(..))")
	public void pt(){}

	//环绕通知
	@Around("pt()")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {  //joinPoint代表我们的目标方法
		Long beginTime = System.currentTimeMillis(); //开始时间
		Object returnValue = joinPoint.proceed(); //执行目标方法，返回他的返回值
		Long endTime = System.currentTimeMillis(); //结束时间
		long time = endTime - beginTime; //执行的时间
		recordLog(joinPoint,time); //保存日志
		return returnValue;
	}

	//保存日志
	private void recordLog(ProceedingJoinPoint joinPoint,long time){
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod(); //获得目标方法
		LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class); //得到目标方法标注的注解
		log.info("===========日志开始===========");
//		log.info("module:{}",logAnnotation.module()); //打印模块名
//		log.info("operator:{}",logAnnotation.operator()); //打印操作名称
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		String operator = "";
		if (methodName.contains("find")){
			operator = "查询操作";
		}else if (methodName.contains("remove")){
			operator = "删除操作";
		}else if (methodName.contains("add")){
			operator = "新增操作";
		}else if (methodName.contains("change")){
			operator = "修改操作";
		}
		log.info("接口名称：{}",className + "." + methodName + "()"); //打印类名和方法名
		log.info("操作名称：{}",operator);; //打印操作
		log.info("执行时间：{} ms",time); //打印执行时间
		log.info("===========日志结束===========");
	}
}
