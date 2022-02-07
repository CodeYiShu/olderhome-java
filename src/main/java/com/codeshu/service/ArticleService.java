package com.codeshu.service;

import com.codeshu.entity.Article;

import java.util.List;

/**
 * @author ShuCode
 * @date 2022/2/4 16:03
 * @Email 13828965090@163.com
 */
public interface ArticleService {
	List<Article> findAll();
	Article findById(Integer id);
	List<Article> findByTitle(String title);
	int add(Article article);
	int remove(Integer id);
}
