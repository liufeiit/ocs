package me.ocs.commons.redis;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年3月8日 下午6:34:43
 */
public interface RedisCallback<T> {
	T doInRedis(Jedis jedis) throws Throwable;
}