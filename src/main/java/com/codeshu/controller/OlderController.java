package com.codeshu.controller;


import com.codeshu.common.Result;
import com.codeshu.entity.Older;
import com.codeshu.service.OlderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
@RequestMapping("/older")
//@RequiresAuthentication
public class OlderController {
	@Autowired
	private OlderService olderService;
	/**
	 * 分页查询
	 * @param pageNum 表示当前第几页,默认第1页
	 * @param pageSize 一页显示多少条记录,默认5条记录
	 * @return 返回一个封装了分页后的记录的分页对象PageInfo
	 */
	@GetMapping(value = "/findAll")
	public Result findAll(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
						  @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize){
		//以及默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Older> olders = olderService.findAll();
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Older> pageInfo = new PageInfo<>(olders);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}


	/**
	 * 根据名称模糊查询老人信息
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	@GetMapping(value = "/findByLikeName")
	public Result findByName(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
							@RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize,
							@RequestParam String name){
		//默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Older> olders = olderService.findByLikeName(name);
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Older> pageInfo = new PageInfo<>(olders);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	/**
	 * 查询紧急情况的老人
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping(value = "/findDangerOlder")
	public Result findDangerOlder(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
								  @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize){
		//默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Older> olders = olderService.findDangerOlder();
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Older> pageInfo = new PageInfo<>(olders);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	/**
	 * 查询观察情况的老人
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping(value = "/findGuanchaOlder")
	public Result findGuanchaOlder(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
								  @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize){
		//默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Older> olders = olderService.findGuanchaOlder();
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Older> pageInfo = new PageInfo<>(olders);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	/**
	 * 根据监护人名称查询老人
	 */
	@GetMapping(value = "/findByGuarderName")
	public Result findByGuarderId(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
								  @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize,
								  @RequestParam String guarderName){
		//默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Older> olders = olderService.findByGuarderName(guarderName);
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Older> pageInfo = new PageInfo<>(olders);
		return Result.success(pageInfo);
	}

	/**
	 * 更新一个老人信息
	 * @param older
	 * @return
	 */
	@PostMapping(value = "/change")
	public Result change(@RequestBody Older older){
		int count = olderService.change(older);
		if(count == 1){
			return Result.success("修改成功");
		}else {
			return Result.fail("修改失败");
		}
	}

	/**
	 * 根据id删除一条老人信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/remove")
	public Result remove(Integer id){
		int count = olderService.remove(id);
		if(count == 1){
			return Result.success("删除成功");
		}else {
			return Result.fail("删除失败");
		}
	}

	/**
	 * 新增一个老人信息
	 * @param older
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result add(@RequestBody Older older){
		int count = olderService.add(older);
		System.out.println(count);
		if(count == 1){
			return Result.success("新增成功");
		}else {
			return Result.fail("新增失败");
		}
	}


}
