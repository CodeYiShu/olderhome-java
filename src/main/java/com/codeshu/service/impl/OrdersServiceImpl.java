package com.codeshu.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.codeshu.config.AliPayConfig;
import com.codeshu.entity.Cost;
import com.codeshu.entity.Orders;
import com.codeshu.mapper.CostMapper;
import com.codeshu.mapper.OrdersMapper;
import com.codeshu.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ShuCode
 * @date 2021/12/15 16:51
 * @Email 13828965090@163.com
 */
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private AliPayConfig alipayConfig;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private CostMapper costMapper;
	/**
	 * 支付接口
	 *
	 * @param orders
	 * @return
	 * @throws AlipayApiException
	 */
	@Override
	public String add(Orders orders) throws AlipayApiException {
		// 支付宝网关
		String serverUrl = alipayConfig.getGatewayUrl();
		// APPID
		String appId = alipayConfig.getAppId();
		// 商户私钥, 即PKCS8格式RSA2私钥
		String privateKey = alipayConfig.getPrivateKey();
		// 格式化为 json 格式
		String format = "json";
		// 字符编码格式
		String charset = alipayConfig.getCharset();
		// 支付宝公钥, 即对应APPID下的支付宝公钥
		String alipayPublicKey = alipayConfig.getPublicKey();
		// 签名方式
		String signType = alipayConfig.getSignType();
		// 页面跳转同步通知页面路径
		String returnUrl = alipayConfig.getReturnUrl();
		// 服务器异步通知页面路径
		String notifyUrl = alipayConfig.getNotifyUrl();

		// 1、获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(
				serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);

		// 2、设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		// 页面跳转同步通知页面路径
		alipayRequest.setReturnUrl(returnUrl);
		// 服务器异步通知页面路径
		alipayRequest.setNotifyUrl(notifyUrl);

		/*
			可以先将订单信息存入order表中
			前台传入costId
		 */
		Integer costId = orders.getCostId();  //获取order中的费用ID得到对应的cost费用
		Cost cost = costMapper.selectById(costId);
		Integer money = cost.getTotal(); //Cost表中的费用
		String uuid = orders.setUuid(IdUtil.simpleUUID()).getUuid(); //订单号
		ordersMapper.insert(orders); //存入数据库先，不修改cost表的状态，还是未缴费状态

		//携带过去支付宝进行支付
		String out_trade_no = uuid;  //订单号
		Integer total_amount = money; //费用（让cost中的费用作为支付的费用）
		String subject = "order";  //描述
		String timeout_express = "10m";
		String description = "";
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ description +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 3、请求支付宝进行付款，并获取支付结果
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		// 返回付款信息
		return result;
	}

	/**
	 * 支付后回调到这里
	 */
	public void aliNotify(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();  //获取所有request的支付消息
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//验证
		boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getPublicKey(), alipayConfig.getCharset(),alipayConfig.getSignType()); //调用SDK验证签名

		if(signVerified) {//验证成功
			//商户订单号，其实是上面那个方法的order的uuid，可以在这里根据uuid查询到刚刚支付的orders订单
			String uuid = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			Orders orders = ordersMapper.selectByUuid(uuid);  //根据uuid查询刚刚支付的订单
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			if (trade_status.equals("TRADE_SUCCESS")){  //如果交易成功
				Integer costId = orders.getCostId();  //获取订单中费用cost的id
				Cost cost = costMapper.selectById(costId);
				cost.setStatus("已缴");  //修改cost的状态为已经缴费
				costMapper.update(cost);  //更新cost
			}
			System.out.println("成功");
		}else {//验证失败
			System.out.println("失败");
		}
	}
}
