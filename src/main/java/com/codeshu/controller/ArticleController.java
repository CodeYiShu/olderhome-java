package com.codeshu.controller;

import com.codeshu.common.Result;
import com.codeshu.entity.Admin;
import com.codeshu.entity.Article;
import com.codeshu.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ShuCode
 * @date 2022/2/4 16:02
 * @Email 13828965090@163.com
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@GetMapping(value = "/findById")
	public Result findById(Integer id){  //根据id查询一篇文章
		Article article = articleService.findById(id);
		return Result.success(article);
	}

	@GetMapping(value = "/findAll")  //分页查询所有文章
	public Result findAll(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
						  @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
		//以及默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		List<Article> articles = articleService.findAll();
		PageInfo<Article> pageInfo = new PageInfo<>(articles);
		//将分页对象返回
		return Result.success(pageInfo);
	}

	@GetMapping(value = "/findByTitle")
	public Result findByTitle(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
							  @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,
							  String title){
		//以及默认显示第1页，1页5行
		PageHelper.startPage(pageNum,pageSize);
		List<Article> articles = articleService.findByTitle(title);
		PageInfo<Article> pageInfo = new PageInfo<>(articles);
		//将分页对象返回
		return Result.success(pageInfo);
	}

	@PostMapping(value = "/add")
	public Result add(@RequestBody Article article){  //管理员发布文章
		Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
		int count = articleService.add(article);
		if (count > 0){
			return Result.success("发布成功");
		}else {
			return Result.fail("发布失败");
		}
	}

	@GetMapping(value = "/remove")
	public Result remove(Integer id){  //删除文章
		int count = articleService.remove(id);
		if (count > 0){
			return Result.success("删除成功");
		}else {
			return Result.fail("删除失败");
		}
	}
}
