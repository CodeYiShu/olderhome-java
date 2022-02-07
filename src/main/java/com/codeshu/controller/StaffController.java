package com.codeshu.controller;


import com.codeshu.common.Result;
import com.codeshu.entity.Older;
import com.codeshu.entity.Staff;
import com.codeshu.service.StaffService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private StaffService staffService;
	/**
	 * 分页查询
	 * @param pageNum 表示当前第几页,默认第1页
	 * @param pageSize 一页显示多少条记录,默认5条记录
	 * @return 返回一个封装了分页后的记录的分页对象PageInfo
	 */
	@GetMapping(value = "/findAll")
	public Result findAll(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
						  @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
		//以及默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Staff> staffs = staffService.findAll();
		System.out.println(staffs);
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Staff> pageInfo = new PageInfo<>(staffs);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	/**
	 * 根据姓名模糊查询医护人员信息
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	@GetMapping(value = "/findByLikeName")
	public Result findByLikeName(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
							 @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize,
							 @RequestParam String name){
		//默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Staff> staffs = staffService.findByLikeName(name);
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Staff> pageInfo = new PageInfo<>(staffs);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	/**
	 * 根据id删除一条医护人员信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/remove")
	public Result remove(Integer id){
		int count = staffService.remove(id);
		if(count == 1){
			return Result.success("删除成功");
		}else {
			return Result.fail("删除失败");
		}
	}

	//新增
	@PostMapping(value = "/add")
	public Result add(@RequestBody Staff staff){
		System.out.println(staff);
		int count = staffService.save(staff);
		if(count == 1){
			return Result.success("新增成功");
		}else {
			return Result.fail("新增失败");
		}
	}

	//更改(一般是管理员更改)
	@PostMapping(value = "/change")
	public Result change(@RequestBody Staff staff){
		System.out.println(staff);
		int count = staffService.change(staff);
		if(count == 1){
			return Result.success("更改成功");
		}else {
			return Result.fail("更改失败");
		}
	}

	//更改个人信息（自己修改，包括密码）
	@PostMapping(value = "/changeInfo")
	public Result changeInfo(@RequestBody Staff staff){
		System.out.println(staff);
		Staff newStaff = staffService.changeInfo(staff);
		if(newStaff != null){
			return Result.success(200,"修改成功咯",newStaff);
		}else {
			return Result.fail("修改成功咯");
		}
	}


}
