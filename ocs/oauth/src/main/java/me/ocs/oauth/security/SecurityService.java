package me.ocs.oauth.security;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月24日 下午4:21:58
 */
public interface SecurityService {
	
	String encrypt(String content) throws SecurityException;
	
	String decrypt(String content) throws SecurityException;
}