package com.codeshu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author ShuCode
 * @date 2022/2/4 15:58
 * @Email 13828965090@163.com
 */
@Data
public class Article {
	private Integer id;  //文章id
	private String title;  //文章标题
	private String content;  //文章内容
	private Admin admin;  //发布文章的管理员
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime time; //发布时间
}
