package com.codeshu.service.impl;

import com.codeshu.entity.Admin;
import com.codeshu.entity.Article;
import com.codeshu.mapper.ArticleMapper;
import com.codeshu.service.ArticleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ShuCode
 * @date 2022/2/4 16:03
 * @Email 13828965090@163.com
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Article> findAll() {
		return articleMapper.selectAll();
	}

	@Override
	public Article findById(Integer id) {
		return articleMapper.selectById(id);
	}

	@Override
	public List<Article> findByTitle(String title) {
		return articleMapper.selectByTitle(title);
	}

	@Override
	public int add(Article article) {  //管理员发布文章
		article.setTime(LocalDateTime.now());  //设置文章发表时间为当前时间
		//从Shiro的主体对象中，得到当前验证的管理员
		Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
		//让此用户作为文章作者
		article.setAdmin(admin);
		return articleMapper.insert(article);
	}

	@Override
	public int remove(Integer id) {
		return articleMapper.delete(id);
	}
}
