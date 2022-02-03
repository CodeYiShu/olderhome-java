package com.codeshu.common.log;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * @author ShuCode
 * @date 2022/1/28 21:29
 * @Email 13828965090@163.com
 */
@Target({ElementType.METHOD})  //表示此注解可以标注在类和方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
	String module() default ""; //模块名称
	String operator() default ""; //操作名称
}
