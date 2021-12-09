package com.codeshu.controller;


import com.codeshu.entity.Admin;
import com.codeshu.entity.Older;
import com.codeshu.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequiresAuthentication
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping("/index")
	public Admin index(){
		//获取在AccountRealm中保存到主体对象的Principal
		Admin admin = (Admin)SecurityUtils.getSubject().getPrincipal();
		System.out.println(admin);
		return admin;
	}



}
