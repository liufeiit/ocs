package me.ocs.oauth.token;

/**
 * 通过序列化和反序列化报文对用户进行授权服务.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月6日 下午10:39:50
 */
public interface SerializerAuthenticationProvider {

	/**
	 * 用户登录
	 */
	String doLogin(String loginRequest);
	
	/**
	 * 用户身份校验
	 */
	String doPrivileged(String privilegedRequest);
}