package com.codeshu.controller.common;

import com.codeshu.common.LoginAndRegisterDto;
import com.codeshu.common.Result;
import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.codeshu.entity.Staff;
import com.codeshu.service.AdminService;
import com.codeshu.service.GuarderService;
import com.codeshu.service.StaffService;
import com.codeshu.shiro.token.UserToken;
import com.codeshu.shiro.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuCode
 * @date 2021/11/5 14:35
 * @Email 13828965090@163.com
 */
@RestController
public class LoginController {
	@Autowired
	AdminService adminService;
	@Autowired
	StaffService staffService;
	@Autowired
	GuarderService guarderService;
	@Autowired
	JwtUtils jwtUtils;

	@RequestMapping("/login")
	public Result login(@Validated @RequestBody LoginAndRegisterDto loginDto, HttpServletResponse response) throws JsonProcessingException {
		String username = null;
		String role = null;
		Subject subject = SecurityUtils.getSubject();
		//根据角色，去登录
		if("1".equals(loginDto.getRole())){ //管理员
			role = "Admin";
			//将用户名和密码传入token，最重要的是角色，来找到对应的Realm进行处理
			UserToken userToken  = new UserToken(loginDto.getUsername(), loginDto.getPassword(),role);
			subject.login(userToken);//调用主体对象的login()进行认证
			Admin admin = (Admin)subject.getPrincipal();  //从主体对象中获取保存的用户信息
			username = admin.getUsername();
		}else if("2".equals(loginDto.getRole())){ //监护人员
			role = "Guarder";
			UserToken userToken  = new UserToken(loginDto.getUsername(), loginDto.getPassword(),role);
			subject.login(userToken);//调用主体对象的login()进行认证
			Guarder guarder = (Guarder)subject.getPrincipal();  //从主体对象中获取保存的用户信息
			username = guarder.getUsername();
		}else { //医护人员
			role = "Staff";
			UserToken userToken  = new UserToken(loginDto.getUsername(), loginDto.getPassword(),role);
			subject.login(userToken);//调用主体对象的login()进行认证
			Staff staff = (Staff)subject.getPrincipal();  //从主体对象中获取保存的用户信息
			username = staff.getUsername();
		}
		//生成一个Jwt，将用户名和角色保存到payload中
		String jwt = jwtUtils.generateToken(username,role);
		//将token 放在我们的header里面
		response.setHeader("Authorization",jwt);
		response.setHeader("Access-control-Expose-Headers","Authorization");
		//将主体对象中保存的用户信息返回去
		return Result.success(subject.getPrincipal());
	}

	//需要认证权限才能退出登录
	@RequiresAuthentication
	@RequestMapping("/logout")
	public Result logout() {
		//调用主体对象的logout退出登录
		SecurityUtils.getSubject().logout();
		return null;
	}

}
