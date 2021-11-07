package com.codeshu.controller;

import cn.hutool.core.lang.Assert;
import com.codeshu.common.LoginAndRegisterDto;
import com.codeshu.common.Result;
import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.codeshu.entity.Staff;
import com.codeshu.service.AdminService;
import com.codeshu.service.GuarderService;
import com.codeshu.service.StaffService;
import com.codeshu.shiro.utils.JwtUtils;
import com.codeshu.shiro.token.UserToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
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
		UserToken userToken = null;
		String username = null;
		Integer id = null;
		String role = null;
		//根据角色，去登录
		if("1".equals(loginDto.getRole())){ //管理员
			Admin admin = adminService.findByName(loginDto.getUsername());
			Assert.notNull(admin,"管理员不存在");//断言拦截
			username = admin.getUsername();
			id = admin.getId();
			role = "Admin";
			//将User对象和密码传入token
			userToken  = new UserToken(loginDto.getUsername(), loginDto.getPassword(),"Admin");
		}else if("2".equals(loginDto.getRole())){ //监护人员
			Guarder guarder = guarderService.findByName(loginDto.getUsername());
			Assert.notNull(guarder,"监护人员不存在");//断言拦截
			username = guarder.getUsername();
			id = guarder.getId();
			role = "Guarder";
			//将User对象和密码传入token
			userToken  = new UserToken(loginDto.getUsername(), loginDto.getPassword(),"Guarder");
		}else { //医护人员
			Staff staff = staffService.findByName(loginDto.getUsername());
			Assert.notNull(staff,"医护人员不存在");//断言拦截
			username = staff.getUsername();
			id = staff.getId();
			role = "Staff";
			//将User对象和密码传入token
			userToken  = new UserToken(loginDto.getUsername(), loginDto.getPassword(),"Staff");
		}
		//调用主体对象的login()进行认证
		SecurityUtils.getSubject().login(userToken);
		//生成一个Jwt，将用户名和角色保存到payload中
		String jwt = jwtUtils.generateToken(username,role);
		//将token 放在我们的header里面
		response.setHeader("Authorization",jwt);
		response.setHeader("Access-control-Expose-Headers","Authorization");
		Map<String,Object> map = new HashMap();
		//将用户id、角色和用户名返回
		map.put("id",id);
		map.put("role",role);
		map.put("username",username);
		return Result.success(map);
	}

}