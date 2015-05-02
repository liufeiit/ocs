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

	public static final String MESSAGE_PROVIDER_NAME = "EMAIL";
	
	private final List<String> attachments;

	private final List<String> inlines;
	
	private boolean html = true;
	
	public MailMessage() {
		super(MESSAGE_PROVIDER_NAME);
		attachments = new ArrayList<String>();
		inlines = new ArrayList<String>();
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

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}
}