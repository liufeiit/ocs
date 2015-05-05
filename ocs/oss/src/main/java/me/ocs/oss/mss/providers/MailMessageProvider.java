package me.ocs.oss.mss.providers;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeMessage;

import me.ocs.oauth.security.SecurityService;
import me.ocs.oss.mss.MessageNotification;
import me.ocs.oss.mss.MessageProvider;
import me.ocs.oss.mss.message.MailMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午11:39:39
 */
public class MailMessageProvider extends JavaMailSenderImpl implements MessageProvider<MailMessage>, InitializingBean {

	private final Log log = LogFactory.getLog(getClass());

	private SecurityService securityService;

	private String secretUsername;

	private String secretPassword;

	@Override
	public String getName() {
		return MailMessage.MESSAGE_PROVIDER_NAME;
	}

	@Override
	public void consume(MailMessage mailMessage, MessageNotification notification) throws Exception {
		String[] mailTo = mailMessage.getMultiTarget();
		if (StringUtils.isBlank(mailMessage.getTarget()) || ArrayUtils.isEmpty(mailTo)) {
			notification.setFailureMessage("未指定消息到达目的地");
			return;
		}
		MimeMessage message = createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(getUsername());
		helper.setTo(mailTo);
		helper.setSubject(mailMessage.getTitle());
		helper.setText(mailMessage.getBody(), mailMessage.isHtml());
		List<String> attachments = mailMessage.getAttachments();
		if (CollectionUtils.isNotEmpty(attachments)) {
			for (String attachment : attachments) {
				File attachFile = new File(attachment);
				FileSystemResource resource = new FileSystemResource(attachFile);
				helper.addAttachment(attachFile.getName(), resource);
			}
		}
		List<String> inlines = mailMessage.getInlines();
		if (CollectionUtils.isNotEmpty(inlines)) {
			for (String inline : inlines) {
				File inlineFile = new File(inline);
				FileSystemResource resource = new FileSystemResource(inlineFile);
				helper.addInline(inlineFile.getName(), resource);
			}
		}
		try {
			send(message);
			notification.setSuccess(true);
			notification.setIdentifier(message.getMessageID());
			log.debug("Send Mail " + message.getMessageID() + " Success!!!");
		} catch (Exception e) {
			notification.setSuccess(false);
			notification.setFailureMessage(ExceptionUtils.getStackTrace(e));
			log.error("Send MailMessage Error.", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.setUsername(securityService.decrypt(secretUsername));
		super.setPassword(securityService.decrypt(secretPassword));
		log.debug("Decrypt Mail Config Success!!!");
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