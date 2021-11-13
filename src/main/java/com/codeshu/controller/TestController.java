package com.codeshu.controller;

import com.codeshu.common.Result;
import com.codeshu.entity.Older;
import com.codeshu.mapper.OlderMapper;
import com.codeshu.service.OlderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuCode
 * @date 2021/11/8 15:50
 * @Email 13828965090@163.com
 */
@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private OlderMapper olderMapper;

	/**
	 * 分页查询
	 * @param pageNum 表示当前第几页,默认第1页
	 * @param pageSize 一页显示多少条记录,默认5条记录
	 * @return 返回一个封装了分页后的记录的分页对象PageInfo
	 */
	@RequestMapping("/findOlderAll")
	public PageInfo<Older> findOlderAll(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
										@RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize){
		//以及默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		//查询出所有的记录
		List<Older> olders = olderMapper.selectList(null);
		//将查询出来的Older集合,进行分页,将分页后的记录封装为分页对象PageInfo
		PageInfo<Older> pageInfo = new PageInfo<>(olders);
		//将分页对象PageInfo返回
		return pageInfo;
	}


	@RequestMapping("/save")
	public void save(@RequestBody Older older, HttpServletRequest request){
		olderMapper.updateById(older);
	}

	@RequestMapping("/delete")
	public void delete(Integer id){
		olderMapper.deleteById(id);
	}

	@RequestMapping("/insert")
	public void insert(@RequestBody Older older){
		olderMapper.insert(older);
	}

	@PostMapping("/user/login")
	public Result userLogin(@RequestBody ResultTest resultTest){
		Map<String,String> map = new HashMap<>();
		map.put("token","123");
		return Result.success(map);
	}
	@RequestMapping("/user/info")
	public Result userInfo(String token){
		Map<String,String> map = new HashMap<>();
		map.put("token","123");
		return Result.success(map);
	}
}
@Data
class ResultTest{
	private String username;
	private String password;
}
