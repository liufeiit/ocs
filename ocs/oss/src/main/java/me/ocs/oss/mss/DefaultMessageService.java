package me.ocs.oss.mss;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * 默认的消息服务实现
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月6日 上午11:30:03
 */
public class DefaultMessageService implements MessageService, InitializingBean {

	protected final Log log = LogFactory.getLog(getClass());

	private final ConcurrentHashMap<String, MessageProvider<Message>> messageProvidersCacheMap = new ConcurrentHashMap<String, MessageProvider<Message>>(3);

	private List<MessageProvider<Message>> messageProviders;

	@Override
	public void registProvider(MessageProvider<Message> provider) {
		messageProvidersCacheMap.put(provider.getName(), provider);
	}

	@Override
	public MessageNotification send(Message message) throws MessageException {
		String providerName = message.getProviderName();
		MessageProvider<Message> messageProvider = messageProvidersCacheMap.get(providerName);
		if (messageProvider == null) {
			throw new MessageException("No MessageProvider Named " + providerName + " for Message " + message.getClass().getSimpleName());
		}
		final MessageNotification messageNotification = new MessageNotification(providerName);
		try {
			messageProvider.consume(message, messageNotification);
		} catch (Exception e) {
			messageNotification.setSuccess(false);
			messageNotification.setFailureMessage(ExceptionUtils.getStackTrace(e));
			log.error("Consume Message Error.", e);
		}
		return messageNotification;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (CollectionUtils.isEmpty(messageProviders)) {
			throw new IllegalStateException("MessageService Acquire MessageProvider.");
		}
		for (MessageProvider<Message> messageProvider : messageProviders) {
			registProvider(messageProvider);
		}
	}

	public void setMessageProviders(List<MessageProvider<Message>> messageProviders) {
		this.messageProviders = messageProviders;
	}
}