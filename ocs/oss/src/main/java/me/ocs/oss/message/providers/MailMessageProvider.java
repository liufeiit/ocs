package me.ocs.oss.message.providers;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeMessage;

import me.ocs.oauth.security.SecurityService;
import me.ocs.oss.message.Message;
import me.ocs.oss.message.MessageNotification;
import me.ocs.oss.message.MessageProvider;
import me.ocs.oss.message.msg.MailMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午6:07:28
 */
public class MailMessageProvider extends JavaMailSenderImpl implements MessageProvider, InitializingBean {

	private SecurityService securityService;

	private String secretUsername;

	private String secretPassword;

	@Override
	public String getName() {
		return MailMessage.MAIL_MESSAGE_PROVIDER_NAME;
	}

	@Override
	public void consume(Message msg, MessageNotification notification) throws Exception {
		MailMessage mailMessage = null;
		if (!(msg instanceof MailMessage)) {
			notification.setFailureMessage("消息类型不正确");
			return;
		}
		mailMessage = MailMessage.class.cast(msg);
		if (StringUtils.isBlank(mailMessage.getTarget())) {
			notification.setFailureMessage("没有指定消息到达目标对象");
			return;
		}
		String[] to = StringUtils.split(mailMessage.getTarget(), ",");
		if (to == null || to.length <= 0) {
			notification.setFailureMessage("没有指定消息到达目标对象");
			return;
		}
		MimeMessage message = createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(getUsername());
		helper.setTo(to);
		helper.setSubject(mailMessage.getTitle());
		helper.setText(mailMessage.getBody(), true);
		List<String> attachments = mailMessage.getAttachments();
		if (attachments != null && !attachments.isEmpty()) {
			for (String attachment : attachments) {
				File attachFile = new File(attachment);
				FileSystemResource resource = new FileSystemResource(attachFile);
				helper.addAttachment(attachFile.getName(), resource);
			}
		}
		List<String> inlines = mailMessage.getInlines();
		if (inlines != null && !inlines.isEmpty()) {
			for (String inline : inlines) {
				File inlineFile = new File(inline);
				FileSystemResource resource = new FileSystemResource(inlineFile);
				helper.addInline(inlineFile.getName(), resource);
			}
		}
		send(message);
		notification.setSuccess(true);
		notification.setIdentifier(message.getMessageID());
		return;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.setUsername(securityService.decrypt(secretUsername));
		super.setPassword(securityService.decrypt(secretPassword));
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setSecretUsername(String secretUsername) {
		this.secretUsername = secretUsername;
	}

	public void setSecretPassword(String secretPassword) {
		this.secretPassword = secretPassword;
	}
}