package com.codeshu.shiro;

import com.codeshu.shiro.token.UserToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 当配置了多个Realm时，我们通常使用的认证器是shiro自带的org.apache.shiro.authc.pam.ModularRealmAuthenticator，其中决定使用的Realm的是doAuthenticate()方法
 *
 * 自定义Authenticator
 * 注意，当需要分别定义处理学生和教师和管理员验证的Realm时，对应Realm的全类名应该包含字符串“Student”“Teacher”，或者“Admin”。
 * 并且，他们不能相互包含，例如，处理学生验证的Realm的全类名中不应该包含字符串"Admin"。
 */
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {

	private static final Logger logger = LoggerFactory.getLogger(UserModularRealmAuthenticator.class);

	@Override
	protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		logger.info("UserModularRealmAuthenticator:method doAuthenticate() execute ");
		String loginType = null;
		// 判断getRealms()是否返回为空
		assertRealmsConfigured();
		//如果token类型是UserToken的，则表示是登录的token
		if(authenticationToken instanceof UserToken){
			// 强制转换回自定义的UserToken
			UserToken token = (UserToken) authenticationToken;
			// 登录类型
			loginType = token.getLoginType();
		}else{
			//如果token类型不是UserToken的，则是认证jwt的
			loginType = "Account";
		}
		// 所有Realm
		Collection<Realm> realms = getRealms();
		// 登录类型对应的所有Realm
		ArrayList<Realm> typeRealms = new ArrayList<>();
		for (Realm realm : realms) {
			//判断哪个Realm的名字带有登录类型的字符串
			if (realm.getName().contains(loginType))
			typeRealms.add(realm);
		}

		// 判断是单Realm还是多Realm
		if (typeRealms.size() == 1){
			logger.info("doSingleRealmAuthentication() execute ");
			return doSingleRealmAuthentication(typeRealms.get(0),authenticationToken);
		}
        else{
			logger.info("doMultiRealmAuthentication() execute ");
			return doMultiRealmAuthentication(typeRealms, authenticationToken);
		}
	}
}

