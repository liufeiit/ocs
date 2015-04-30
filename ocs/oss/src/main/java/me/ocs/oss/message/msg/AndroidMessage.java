package me.ocs.oss.message.msg;

import me.ocs.oss.message.Message;

/**
 * Android消息.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午5:56:24
 */
public class AndroidMessage extends Message {
	
	public static final String ANDROID_MESSAGE_PROVIDER_NAME = "ANDROID";

	public AndroidMessage() {
		super(ANDROID_MESSAGE_PROVIDER_NAME);
	}
}