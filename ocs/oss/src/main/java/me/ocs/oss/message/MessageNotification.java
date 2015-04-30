package me.ocs.oss.message;

/**
 * 系统发送结果.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午4:55:52
 */
public class MessageNotification {

	/**
	 * 消息供应商头信息
	 */
	private final String provider;
	
	/**
	 * 消息达到目的地
	 */
	private String target;
	
	/**
	 * 消息ID
	 */
	private String identifier;
	
	/**
	 * 消息失效时间, 单位:秒
	 */
	private long expiresInSec;
	
	/**
	 * 翻译之后原生消息
	 */
	private byte[] marshall;
	
	/**
	 * 附件
	 */
	private Object attachment;
	
	/**
	 * 是否成功
	 */
	private boolean success = false;
	
	/**
	 * 失败消息
	 */
	private String failureMessage;
	
	public MessageNotification(String provider) {
		super();
		this.provider = provider;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getAttachment() {
		return attachment;
	}

	public void setAttachment(Object attachment) {
		this.attachment = attachment;
	}

	public String getProviderName() {
		return provider;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public long getExpiresInSec() {
		return expiresInSec;
	}

	public void setExpiresInSec(long expiresInSec) {
		this.expiresInSec = expiresInSec;
	}

	public byte[] getMarshall() {
		return marshall;
	}

	public void setMarshall(byte[] marshall) {
		this.marshall = marshall;
	}
}