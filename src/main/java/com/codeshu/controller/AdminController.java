package com.codeshu.controller;


import com.codeshu.entity.Admin;
import com.codeshu.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

	@RequiresAuthentication
	@RequestMapping("/index")
	public Admin index(){
		//获取在AccountRealm中保存到主体对象的Principal
		Admin admin = (Admin)SecurityUtils.getSubject().getPrincipal();
		System.out.println(admin);
		return adminService.getById(1L);
	}
}
