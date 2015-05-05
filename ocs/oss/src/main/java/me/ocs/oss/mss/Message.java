package me.ocs.oss.mss;

import org.apache.commons.lang3.StringUtils;

/**
 * 消息.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午4:56:15
 */
public abstract class Message {

	public static final String TARGET_SPLIT = ",";

	/**
	 * 消息供应商头信息
	 */
	private final String provider;

	/**
	 * 消息达到目的地
	 */
	private String target;

	/**
	 * 消息标题
	 */
	private String title;

	/**
	 * 消息体
	 */
	private String body;

	public Message(String provider) {
		super();
		this.provider = provider;
	}

	public String getProviderName() {
		return provider;
	}

	public String[] getMultiTarget() {
		if (StringUtils.isBlank(target)) {
			return null;
		}
		return StringUtils.split(target, TARGET_SPLIT);
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}