package me.ocs.oauth.token;

import me.ocs.commons.serializer.GsonSerializer;
import me.ocs.commons.serializer.Serializer;

/**
 * 通过JSON报文对用户进行授权服务.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月6日 下午10:40:06
 */
public class JsonRedisAuthenticationProvider extends SerializerRedisAuthenticationProvider {

	private static final Serializer SERIALIZER = new GsonSerializer();

	public JsonRedisAuthenticationProvider() {
		super(SERIALIZER);
	}
}