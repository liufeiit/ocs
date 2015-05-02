package me.ocs.oss.message.providers;

import me.ocs.oss.message.Message;
import me.ocs.oss.message.MessageNotification;
import me.ocs.oss.message.MessageProvider;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月2日 下午7:57:41
 */
public abstract class AbstractMessageProvider<M extends Message> implements MessageProvider<M>, InitializingBean, DisposableBean {

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public final void afterPropertiesSet() throws Exception {
		try {
			init();
		} catch (Exception e) {
			log.error(getName() + " MessageProvider Init Error.", e);
			throw new IllegalStateException(getName() + " MessageProvider Init Error.", e);
		}
	}
	
	protected void init() throws Exception {
		
	}

	@Override
	public final void consume(M message, MessageNotification notification) throws Exception {
		try {
			if(StringUtils.isBlank(message.getTarget())) {
				notification.setSuccess(false);
				notification.setFailureMessage("未指定消息到达目的地");
				return;
			}
			onConsume(message, notification);
		} catch (Exception e) {
			notification.setSuccess(false);
			notification.setFailureMessage(e.getLocalizedMessage());
		}
	}
	
	protected abstract void onConsume(M message, MessageNotification notification) throws Exception;

	@Override
	public final void destroy() throws Exception {
		try {
			destroy0();
		} catch (Exception e) {
			log.error(getName() + " MessageProvider Destroy Error.", e);
		}
	}
	
	protected void destroy0() throws Exception {
		
	}
}