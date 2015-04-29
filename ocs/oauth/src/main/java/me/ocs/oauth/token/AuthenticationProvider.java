package me.ocs.oauth.token;

import me.ocs.oauth.token.request.LoginRequest;
import me.ocs.oauth.token.request.PrivilegedRequest;
import me.ocs.oauth.token.response.LoginResponse;
import me.ocs.oauth.token.response.PrivilegedResponse;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年3月8日 下午6:38:51
 */
public interface AuthenticationProvider {

	LoginResponse doLogin(LoginRequest request);
	
	PrivilegedResponse doPrivileged(PrivilegedRequest request);
}