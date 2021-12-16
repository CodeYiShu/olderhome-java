package com.codeshu.controller;


import com.codeshu.common.Result;
import com.codeshu.entity.Admin;
import com.codeshu.entity.Guarder;
import com.codeshu.entity.Staff;
import com.codeshu.service.GuarderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/guarder")
public class GuarderController {
	@Autowired
	private GuarderService guarderService;

	/**
	 * 根据名称查看一个监护人
	 * @return
	 */
	@GetMapping("/findByName")
	public Result findByName(String name){
		Guarder guarder = guarderService.findByName(name);
		return Result.success(guarder);
	}
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
		List<Guarder> guarders = guarderService.findAll();
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Guarder> pageInfo = new PageInfo<>(guarders);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	/**
	 * 根据名称模糊查询监护人员信息
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
		List<Guarder> guarders = guarderService.findByLikeName(name);
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Guarder> pageInfo = new PageInfo<>(guarders);
		//将分页对象PageInfo返回
		return Result.success(pageInfo);
	}

	/**
	 * 根据id删除一条监护人员
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/remove")
	public Result remove(Integer id){
		int count = guarderService.remove(id);
		if(count == 1){
			return Result.success("删除成功");
		}else {
			return Result.fail("删除失败");
		}
	}

	//新增
	@PostMapping(value = "/add")
	public Result add(@RequestBody Guarder guarder){
		System.out.println(guarder);
		int count = guarderService.add(guarder);
		if(count == 1){
			return Result.success("新增成功");
		}else {
			return Result.fail("新增失败");
		}
	}

	//更改（管理员的修改）
	@PostMapping(value = "/change")
	public Result change(@RequestBody Guarder guarder){
		System.out.println(guarder);
		int count = guarderService.change(guarder);
		if(count == 1){
			return Result.success("更改成功");
		}else {
			return Result.fail("更改失败");
		}
	}

	//更改个人信息（个人的更改，包括自己的密码）
	@PostMapping(value = "/changeInfo")
	public Result changeInfo(@RequestBody Guarder guarder, HttpServletResponse response){
		Guarder newGuarder = guarderService.changeInfo(guarder);  //返回新的Guarder
		if(newGuarder != null){
			return Result.success(200,"修改成功咯",newGuarder);
		}else {
			return Result.fail("修改失败咯");
		}
	}

	@GetMapping("/addOlder") //新增监护的老人
	public Result addOlder(String guarderId,String olderName){ //根据老人名称修改他的监护人ID
		System.out.println(olderName + "：" + guarderId);
		int count = guarderService.addOlder(guarderId,olderName);
		if(count == 1){
			return Result.success("新增成功");
		}else {
			return Result.fail("新增失败，可能当前老人已被绑定");
		}
	}

}
