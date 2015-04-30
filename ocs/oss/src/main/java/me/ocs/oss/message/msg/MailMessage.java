package me.ocs.oss.message.msg;

import java.util.ArrayList;
import java.util.List;

import me.ocs.oss.message.Message;

/**
 * 邮件消息.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午5:57:57
 */
public class MailMessage extends Message {

	public static final String MAIL_MESSAGE_PROVIDER_NAME = "EMAIL";
	
	private final List<String> attachments = new ArrayList<String>();

	private final List<String> inlines = new ArrayList<String>();
	
	public MailMessage() {
		super(MAIL_MESSAGE_PROVIDER_NAME);
	}
	
	public MailMessage addAttachment(String attachment) {
		attachments.add(attachment);
		return this;
	}
	
	public MailMessage addInlines(String inline) {
		inlines.add(inline);
		return this;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public List<String> getInlines() {
		return inlines;
	}
}