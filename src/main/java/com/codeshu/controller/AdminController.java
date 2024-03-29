package com.codeshu.controller;


import com.codeshu.common.Result;
import com.codeshu.entity.Admin;
import com.codeshu.service.AdminService;
import com.codeshu.shiro.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author codeshu
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	JwtUtils jwtUtils;

	@RequestMapping("/index")
	public Result index(){
		//获取在AccountRealm中保存到主体对象的Principal
		Admin admin = (Admin)SecurityUtils.getSubject().getPrincipal();
		System.out.println(admin);
		return Result.success(admin);
	}

	@PostMapping("/test")
	public Result test(@RequestBody Admin admin){
		return Result.success(admin);
	}

	@PostMapping("/changeInfo")  //修改个人信息
	public Result changeInfo(@RequestBody Admin admin, HttpServletResponse response){
		Admin newAdmin = adminService.changeInfo(admin);  //返回新的Admin
		if(newAdmin != null){
			return Result.success(200,"修改成功咯",newAdmin);
		}else {
			return Result.fail("修改失败咯");
		}
	}

}
