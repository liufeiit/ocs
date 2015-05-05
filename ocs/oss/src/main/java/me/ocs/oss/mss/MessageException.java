package me.ocs.oss.mss;

/**
 * 消息发送异常.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午4:59:28
 */
public class MessageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MessageException() {
		super();
	}

	public MessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageException(String message) {
		super(message);
	}

	public MessageException(Throwable cause) {
		super(cause);
	}
}