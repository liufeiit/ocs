package me.ocs.commons.redis;

import redis.clients.jedis.Jedis;

/**
 * Redis连接接口定义类。
 * 
 * @author fei.liu E-mail:fei.liu@andpay.me
 * 
 * @version 1.0.0
 * @since 2015年1月28日 下午1:53:20
 */
public interface RedisConnection {
	/**
	 * 获得Jedis对象
	 * 
	 * @return
	 */
	Jedis getJedis();

	/**
	 * 关闭正常连接
	 */
	void close();

	/**
	 * 关闭异常连接
	 */
	void closeBroken();

	/**
	 * 强制关闭连接
	 */
	void forceClose();
}