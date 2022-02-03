package com.codeshu.controller;

import com.codeshu.common.log.LogAnnotation;
import com.codeshu.common.Result;
import com.codeshu.entity.Bed;
import com.codeshu.service.BedService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ShuCode
 * @date 2021/12/8 12:52
 * @Email 13828965090@163.com
 */
@RestController
@RequestMapping("/bed")
public class BedController {
	@Autowired
	private BedService bedService;

	/**
	 * 分页查询
	 * @param pageNum 表示当前第几页,默认第1页
	 * @param pageSize 一页显示多少条记录,默认5条记录
	 * @return 返回一个封装了分页后的记录的分页对象PageInfo
	 */
	@GetMapping(value = "/findAll")
	@LogAnnotation(module = "床位",operator = "查询所有")
	public Result findAll(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
						  @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize){
		//以及默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Bed> bedList = bedService.findAll();
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Bed> pageInfo = new PageInfo<>(bedList);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	//根据床位号来模糊查询
	@GetMapping(value = "/findByNum")
	public Result findByNum(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
							 @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize,
							 @RequestParam String num){
		//默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Bed> bedList = bedService.findByNum(num);
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Bed> pageInfo = new PageInfo<>(bedList);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	/**
	 * 更新一个床位信息
	 * @param older
	 * @return
	 */
	@PostMapping(value = "/change")
	public Result change(@RequestBody Bed bed){
		int count = bedService.change(bed);
		if(count == 1){
			return Result.success("修改成功");
		}else {
			return Result.fail("修改失败，所住老人不存在");
		}
	}

	/**
	 * 根据id删除一条床位信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/remove")
	public Result remove(Integer id){
		int count = bedService.remove(id);
		if(count == 1){
			return Result.success("删除成功");
		}else {
			return Result.fail("删除失败");
		}
	}

	/**
	 * 新增一个床位信息
	 * @param older
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result add(@RequestBody Bed bed){
		int count = bedService.add(bed);
		System.out.println(count);
		if(count == 1){
			return Result.success("新增成功");
		}else {
			return Result.fail("新增失败，床位已存在或所住老人不存在");
		}
	}
}
