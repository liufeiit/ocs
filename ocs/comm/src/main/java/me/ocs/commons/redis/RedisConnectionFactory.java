package me.ocs.commons.redis;

/**
 * Redis实现的连接工厂接口定义类。
 * 
 * @author fei.liu E-mail:fei.liu@andpay.me
 * 
 * @version 1.0.0
 * @since 2015年1月28日 下午1:50:18
 */
public interface RedisConnectionFactory {
	/**
	 * 获得Redis连接
	 * 
	 * @return
	 */
	RedisConnection getConnection();
}
