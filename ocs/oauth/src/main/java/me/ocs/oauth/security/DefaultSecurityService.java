package me.ocs.oauth.security;

import java.security.GeneralSecurityException;

import me.ocs.commons.utils.Charsets;
import me.ocs.commons.utils.DES;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月24日 下午4:31:14
 */
public class DefaultSecurityService implements SecurityService {
	
	private final Log log = LogFactory.getLog(getClass());
	
	private String secretKey;
	
	private byte[] key;

	/**
	 * {@link SecurityKeyGenerator#keyEncryptGenerator(int)}
	 */
	private byte[] keyGenerator() {
		if(key != null) {
			return key;
		}
		if(secretKey == null) {
			throw new SecurityException("Property 'secretKey' Acquired.");
		}
		key = Base64.decodeBase64(secretKey.getBytes(Charsets.UTF_8));
		return key;
	}

	@Override
	public String encrypt(String content) throws SecurityException {
		try {
			return DES.encrypt(content, keyGenerator());
		} catch (GeneralSecurityException e) {
			log.error("Encrypt data [" + content + "] Error.", e);
			throw new SecurityException("Encrypt data [" + content + "] Error.", e);
		}
	}

	@Override
	public String decrypt(String content) throws SecurityException {
		try {
			return DES.decrypt(content, keyGenerator());
		} catch (GeneralSecurityException e) {
			log.error("Decrypt data [" + content + "] Error.", e);
			throw new SecurityException("Decrypt data [" + content + "] Error.", e);
		}
	}
	
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}