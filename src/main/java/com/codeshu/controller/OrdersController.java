package com.codeshu.controller;

import com.alipay.api.AlipayApiException;
import com.codeshu.common.Result;
import com.codeshu.config.AliPayConfig;
import com.codeshu.entity.Orders;
import com.codeshu.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author ShuCode
 * @date 2021/12/15 17:05
 * @Email 13828965090@163.com
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	AliPayConfig alipay_public_key;

	/**
	 * 支付
	 * @param orders
	 * @return
	 * @throws AlipayApiException
	 */
	@PostMapping(value = "/alipay")
	public Result alipay(@RequestBody Orders orders) throws AlipayApiException {
		System.out.println(orders);
		return Result.success(200,"操作成功", ordersService.add(orders));
	}

	/**
	 * 支付后回调到这里，可以从request中获取相关信息
	 * @param request
	 * @param
	 * @throws UnsupportedEncodingException
	 * @throws AlipayApiException
	 */
	@RequestMapping("/notify")
	public void notify(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
		ordersService.aliNotify(request);
	}
}
