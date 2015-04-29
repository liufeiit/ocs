package me.ocs.oauth.security;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月24日 下午4:28:44
 */
public class SecurityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SecurityException() {
		super();
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}
}