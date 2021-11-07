package com.codeshu.controller;

import com.codeshu.common.LoginAndRegisterDto;
import com.codeshu.common.Result;
import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.codeshu.entity.Staff;
import com.codeshu.service.AdminService;
import com.codeshu.service.GuarderService;
import com.codeshu.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShuCode
 * @date 2021/11/5 15:43
 * @Email 13828965090@163.com
 */
@RestController
public class RegisterController {
	@Autowired
	AdminService adminService;
	@Autowired
	StaffService staffService;
	@Autowired
	GuarderService guarderService;

	@RequestMapping("/register")
	public Result register(@RequestBody  LoginAndRegisterDto loginDto){
		try{
			if("1".equals(loginDto.getRole())){
				Admin admin = new Admin();
				admin.setUsername(loginDto.getUsername());
				admin.setPassword(loginDto.getPassword());
				adminService.insert(admin);
			}else if("2".equals(loginDto.getRole())){
				Guarder guarder = new Guarder();
				guarder.setUsername(loginDto.getUsername());
				guarder.setPassword(loginDto.getPassword());
				guarderService.insert(guarder);
			}else {
				Staff staff = new Staff();
				staff.setUsername(loginDto.getUsername());
				staff.setPassword(loginDto.getPassword());
				staffService.insert(staff);
			}
			//如果响应成功数据
			return Result.success("注册成功");
		}catch (Exception e){
			e.printStackTrace();
			//有异常则响应异常数据
			return Result.fail("注册失败");
		}
	}
}
