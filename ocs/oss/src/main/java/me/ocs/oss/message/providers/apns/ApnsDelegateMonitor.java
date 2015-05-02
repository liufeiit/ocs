package me.ocs.oss.message.providers.apns;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.notnoop.apns.ApnsDelegate;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.DeliveryError;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月2日 下午8:07:48
 */
public class ApnsDelegateMonitor implements ApnsDelegate {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	public void messageSent(ApnsNotification message, boolean resent) {
		log.error("Sent message " + message + " Resent: " + resent);
	}

	@Override
	public void messageSendFailed(ApnsNotification message, Throwable e) {
		log.error("Failed message " + message, e);
	}

	@Override
	public void connectionClosed(DeliveryError e, int messageIdentifier) {
		log.error("Closed connection: " + messageIdentifier + "\n   deliveryError " + e.toString());
	}

	@Override
	public void cacheLengthExceeded(int newCacheLength) {
		log.error("CacheLengthExceeded " + newCacheLength);
	}

	@Override
	public void notificationsResent(int resendCount) {
		log.error("NotificationResent " + resendCount);
	}
}