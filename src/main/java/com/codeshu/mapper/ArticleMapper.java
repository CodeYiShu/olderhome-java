package com.codeshu.mapper;

import com.codeshu.entity.Article;

import java.util.List;

/**
 * @author ShuCode
 * @date 2022/2/4 16:03
 * @Email 13828965090@163.com
 */
public interface ArticleMapper {
	public List<Article> selectAll();
	public Article selectById(Integer id);
	public int insert(Article article);
	public List<Article> selectByTitle(String title);
	public int delete(Integer id);
}
