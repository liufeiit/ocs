package me.ocs.commons.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 缺省Redis连接实现类。
 * 
 * @author fei.liu E-mail:fei.liu@andpay.me
 * 
 * @version 1.0.0
 * @since 2015年1月28日 下午1:54:45
 */
public class DefaultRedisConnection implements RedisConnection {
	private Log log = LogFactory.getLog(getClass());

	/**
	 * Jedis对象
	 */
	private Jedis jedis;

	/**
	 * Jedis池对象
	 */
	private JedisPool pool;

	public DefaultRedisConnection(JedisPool pool) {
		this.pool = pool;
		jedis = this.pool.getResource();
	}

	public Jedis getJedis() {
		return jedis;
	}

	public void close() {
		try {
			pool.returnResource(jedis);
		} catch (Throwable e) {
			log.error("Return jedis resource meet error.", e);
		}
	}

	public void closeBroken() {
		try {
			pool.returnBrokenResource(jedis);
		} catch (Throwable e) {
			log.error("Return jedis resource meet error.", e);
		}
	}

	public void forceClose() {
		try {
			jedis.disconnect();
		} catch (Throwable e) {
		}
	}

}
