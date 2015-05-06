package me.ocs.oauth.token;

import me.ocs.commons.serializer.Serializer;
import me.ocs.oauth.token.request.LoginRequest;
import me.ocs.oauth.token.request.PrivilegedRequest;
import me.ocs.oauth.token.response.LoginResponse;
import me.ocs.oauth.token.response.PrivilegedResponse;

/**
 * 通过序列化和反序列化报文对用户进行授权服务.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月6日 下午10:40:06
 */
public class SerializerRedisAuthenticationProvider extends RedisAuthenticationProvider implements SerializerAuthenticationProvider {

	private final Serializer serializer;
	
	public SerializerRedisAuthenticationProvider(Serializer serializer) {
		super();
		this.serializer = serializer;
	}

	@Override
	public String doLogin(String loginRequest) {
		LoginRequest request = serializer.deserialize(LoginRequest.class, loginRequest);
		if (request == null) {
			return serializer.serialize(LoginResponse.DEFAULT_RESPONSE);
		}
		return serializer.serialize(super.doLogin(request));
	}

	@Override
	public String doPrivileged(String privilegedRequest) {
		PrivilegedRequest request = serializer.deserialize(PrivilegedRequest.class, privilegedRequest);
		if (request == null) {
			return serializer.serialize(PrivilegedResponse.DEFAULT_RESPONSE);
		}
		return serializer.serialize(super.doPrivileged(request));
	}
}