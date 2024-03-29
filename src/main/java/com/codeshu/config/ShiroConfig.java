package com.codeshu.config;

import com.codeshu.shiro.JwtFilter;
import com.codeshu.shiro.UserModularRealmAuthenticator;
import com.codeshu.shiro.realm.AccountRealm;
import com.codeshu.shiro.realm.AdminRealm;
import com.codeshu.shiro.realm.GuarderRealm;
import com.codeshu.shiro.realm.StaffRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro的配置
 *
 * @author ShuCode
 * @date 2021/11/5 14:28
 * @Email 13828965090@163.com
 */
@Configuration
public class ShiroConfig {
	/**
	 * 注入JwtFilter
	 */
	@Autowired
	private JwtFilter jwtFilter;


	/**
	 * 解决循环依赖
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/**
	 * 启用shiro授权注解拦截方式，AOP式方法级权限检查
	 */
	@Bean
	@DependsOn(value = "lifecycleBeanPostProcessor") //依赖其他bean的初始化
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	/**
	 * 配置ShiroFilter
	 */
	@Bean("shiroFilterFactoryBean")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
														 ShiroFilterChainDefinition shiroFilterChainDefinition) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		//设置安全管理器给ShiroFilter
		shiroFilter.setSecurityManager(securityManager);
		//设置JWTFilter给ShiroFilter，命名为jwt，所有请求都会经过他，除了放行的
		Map<String, Filter> filters = new HashMap<>();
		filters.put("jwt", jwtFilter);
		shiroFilter.setFilters(filters);
		//设置过滤器链
		Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();
		shiroFilter.setFilterChainDefinitionMap(filterMap);
		return shiroFilter;
	}

	/**
	 * 配置安全管理器
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(AccountRealm accountRealm) {
		//设置自定义Realm给安全管理器
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);
		ThreadContext.bind(securityManager);
		//设置多Realm
		securityManager.setAuthenticator(modularRealmAuthenticator());
		List<Realm> realms = new ArrayList<>();
		//添加多个Realm
		realms.add(adminRealm());
		realms.add(staffRealm());
		realms.add(guarderRealm());
		realms.add(accountRealm);
		securityManager.setRealms(realms);

		return securityManager;
	}

	/**
	 * 设置过滤器链
	 */
	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		Map<String, String> filterMap = new LinkedHashMap<>();
		//让所有请求经过名字为jwt的JWTFilter过滤器
		filterMap.put("/**", "jwt");
		chainDefinition.addPathDefinitions(filterMap);
		return chainDefinition;
	}


	/**
	 * 系统自带的Realm管理，主要针对多realm
	 */
	@Bean
	public ModularRealmAuthenticator modularRealmAuthenticator() {
		//自己重写的ModularRealmAuthenticator
		UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
		modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		return modularRealmAuthenticator;
	}

	/**
	 * 多Realm
	 */
	@Bean
	public AdminRealm adminRealm() {
		AdminRealm adminRealm = new AdminRealm();
		//设置加密密码匹配器，用于解密
		adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return adminRealm;
	}

	@Bean
	public StaffRealm staffRealm() {
		StaffRealm staffRealm = new StaffRealm();
		//设置加密密码匹配器，用于解密
		staffRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return staffRealm;
	}

	@Bean
	public GuarderRealm guarderRealm() {
		GuarderRealm guarderRealm = new GuarderRealm();
		//设置加密密码匹配器，用于解密
		guarderRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return guarderRealm;
	}

	@Bean
	public AccountRealm accountRealm() {
		AccountRealm accountRealm = new AccountRealm();
		//此Realm用于校验Jwt，不需要使用加密密码匹配器
		accountRealm.setCredentialsMatcher(new SimpleCredentialsMatcher());
		return accountRealm;
	}

	/**
	 * 加密密码匹配器，需要为其设置散列算法、散列的次数，随机盐则在数据库中查询出来
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		//散列的次数，注册时Service中也要保持是1024
		hashedCredentialsMatcher.setHashIterations(1024);
		return hashedCredentialsMatcher;
	}
}
