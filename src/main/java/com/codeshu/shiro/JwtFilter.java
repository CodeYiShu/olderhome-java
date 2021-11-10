package com.codeshu.shiro;

import com.codeshu.shiro.token.JwtToken;
import com.codeshu.shiro.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author ShuCode
 * @date 2021/11/5 14:27
 * @Email 13828965090@163.com
 */
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {
	@Autowired
	JwtUtils jwtUtils;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 如果带有 token，则对 token 进行检查，否则直接通过
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
		//判断请求的请求头是否带上 "Authorization"
		if (isLoginAttempt(request, response)) {
			//如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
			try {
				//执行认证
				executeLogin(request, response);
				return true;
			} catch (Exception e) {
				//token 错误
				responseError(response, e.getMessage());
			}
		}
		//如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
		return true;
	}
	/**
	 * 判断用户是否想要登入。
	 * 检测 header 里面是否包含 Token 字段
	 */
	@Override
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		String token = req.getHeader("Authorization");
		return token != null;
	}

	/**
	 * 执行登陆操作
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//从请求头中获取出Token
		String token = httpServletRequest.getHeader("Authorization");
		//将字符串形式的Token转为JwtToken类型的令牌
		JwtToken jwtToken = new JwtToken(token);
		//调用主体对象的login()，传入Token提交给realm进行登入，如果错误他会抛出异常并被捕获
		//因为此方法需要AuthenticationToken类型的，而JwtToken就实现了他
		getSubject(request, response).login(jwtToken);
		// 如果没有抛出异常则代表登入成功，返回true
		return true;
	}

	/**
	 * 将非法请求跳转到 /unauthorized/**
	 */
	private void responseError(ServletResponse response, String message) {
		try {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			//设置编码，否则中文字符在重定向时会变为空字符串
			message = URLEncoder.encode(message, "UTF-8");
			httpServletResponse.sendRedirect("/unauthorized/" + message);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 对跨域提供支持
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
		httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
		// 跨域时会首先发送一个OPTIONS请求,这里我们给OPTIONS请求直接返回正常状态
		if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
			return false;
		}
		return super.preHandle(request, response);
	}

}
