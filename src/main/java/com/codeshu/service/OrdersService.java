package com.codeshu.service;

import com.alipay.api.AlipayApiException;
import com.codeshu.entity.Orders;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author ShuCode
 * @date 2021/12/15 16:50
 * @Email 13828965090@163.com
 */
public interface OrdersService {
	public String add(Orders orders) throws AlipayApiException;
	public void aliNotify(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException;
}
