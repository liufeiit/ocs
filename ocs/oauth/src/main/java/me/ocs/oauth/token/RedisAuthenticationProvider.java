package me.ocs.oauth.token;

import me.ocs.commons.redis.RedisCallback;
import me.ocs.commons.redis.RedisTemplate;
import me.ocs.commons.sequence.SequenceService;
import me.ocs.oauth.security.SecurityService;
import me.ocs.oauth.token.request.LoginRequest;
import me.ocs.oauth.token.request.PrivilegedRequest;
import me.ocs.oauth.token.response.LoginResponse;
import me.ocs.oauth.token.response.PrivilegedResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年3月8日 下午6:40:29
 */
public class RedisAuthenticationProvider implements AuthenticationProvider {
	
	private static final String DOT = ",";

	public static final String REDIS_AUTHENTICATION_SEQUENCETYPE = "system.provider.ras";

	private static final int DEFAULT_TOKEN_EXPIRES_IN_SEC = 60 * 60 * 24 * 30;

	protected final Log log = LogFactory.getLog(getClass());

	private RedisTemplate redisTemplate;
	
	private SequenceService sequenceService;
	
	private SecurityService securityService;
	
	private int tokenExpiresInSec = DEFAULT_TOKEN_EXPIRES_IN_SEC;
	
	@Override
	public LoginResponse doLogin(LoginRequest request) {
		final String appId = request.getApp_id();
		final String secretId = request.getSecret_id();
		final String open_id = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(Jedis jedis) throws Throwable {
				String openId = sequenceService.nextValueAsString(REDIS_AUTHENTICATION_SEQUENCETYPE, 25);
				jedis.hset(appId, openId, secretId);
				return openId;
			}
		});
		return redisTemplate.execute(new RedisCallback<LoginResponse>() {
			@Override
			public LoginResponse doInRedis(Jedis jedis) throws Throwable {
				String access_token = securityService.encrypt(appId + DOT + secretId + DOT + open_id);
				jedis.setex(open_id, tokenExpiresInSec, access_token);
				return new LoginResponse(appId, open_id, access_token, tokenExpiresInSec);
			}
		}, LoginResponse.DEFAULT_RESPONSE);
	}

	@Override
	public PrivilegedResponse doPrivileged(PrivilegedRequest request) {
		final String appId = request.getApp_id();
		final String open_id = request.getOpen_id();
		final String access_token = request.getAccess_token();
		return redisTemplate.execute(new RedisCallback<PrivilegedResponse>() {
			@Override
			public PrivilegedResponse doInRedis(Jedis jedis) throws Throwable {
				String _access_token = jedis.get(open_id);
				if (StringUtils.isEmpty(_access_token)) {
					return PrivilegedResponse.DEFAULT_RESPONSE;
				}
				if (!StringUtils.equals(_access_token, access_token)) {
					return PrivilegedResponse.DEFAULT_RESPONSE;
				}
				String secretId = jedis.hget(appId, open_id);
				if (StringUtils.isEmpty(secretId)) {
					return PrivilegedResponse.DEFAULT_RESPONSE;
				}
				return new PrivilegedResponse(secretId);
			}
		}, PrivilegedResponse.DEFAULT_RESPONSE);
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public void setSequenceService(SequenceService sequenceService) {
		this.sequenceService = sequenceService;
	}
	
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	public void setTokenExpiresInSec(int tokenExpiresInSec) {
		this.tokenExpiresInSec = tokenExpiresInSec;
	}
}