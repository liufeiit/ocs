package me.ocs.oss.mss.providers;

import me.ocs.oss.mss.MessageNotification;
import me.ocs.oss.mss.message.IosMessage;
import me.ocs.oss.mss.providers.apns.ApnsDelegateMonitor;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsDelegate;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;
import com.notnoop.apns.PayloadBuilder;
import com.notnoop.apns.ReconnectPolicy;
import com.notnoop.apns.internal.ReconnectPolicies;

/**
 * IOS消息推送.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午11:49:30
 */
public class IosMessageProvider extends AbstractMessageProvider<IosMessage> {

	private ApnsService apnsService;

	private boolean production = true;

	private boolean autoAdjustCacheLength = true;

	private int readTimeout = 10000;

	private String certificate;

	private String password;

	private ReconnectPolicy reconnectPolicy;

	private ApnsDelegate apnsDelegate;

	@Override
	public void init() throws Exception {
		if (apnsService != null) {
			return;
		}
		ApnsServiceBuilder apnsServiceBuilder = APNS.newService()
				.withAppleDestination(production)
				.withAutoAdjustCacheLength(autoAdjustCacheLength)
				.withReadTimeout(readTimeout)
				.withCert(certificate, password);
		if (reconnectPolicy == null) {
			reconnectPolicy = new ReconnectPolicies.EveryHalfHour();
		}
		apnsServiceBuilder.withReconnectPolicy(reconnectPolicy);
		if (apnsDelegate == null) {
			apnsDelegate = new ApnsDelegateMonitor();
		}
		apnsServiceBuilder = apnsServiceBuilder.withDelegate(apnsDelegate);
		apnsService = apnsServiceBuilder.build();
		apnsService.start();
	}

	@Override
	public String getName() {
		return IosMessage.MESSAGE_PROVIDER_NAME;
	}

	@Override
	protected void onConsume(IosMessage message, MessageNotification notification) throws Exception {
		PayloadBuilder payloadBuilder = APNS.newPayload();
		if (StringUtils.isNotBlank(message.getTitle())) {
			payloadBuilder.alertTitle(message.getTitle());
		}
		if (StringUtils.isNotBlank(message.getBody())) {
			payloadBuilder.alertBody(message.getBody());
		}
		if (StringUtils.isNotBlank(message.getAction())) {
			payloadBuilder.alertAction(message.getAction());
		}
		if (ArrayUtils.isNotEmpty(message.getUrlArgs())) {
			payloadBuilder.urlArgs(message.getUrlArgs());
		}
		if (StringUtils.isNotBlank(message.getSound())) {
			payloadBuilder.sound(message.getSound());
		}
		if (StringUtils.isNotBlank(message.getCategory())) {
			payloadBuilder.category(message.getCategory());
		}
		if (message.getBadge() > 0) {
			payloadBuilder.badge(message.getBadge());
		}
		if (StringUtils.isNotBlank(message.getLaunchImage())) {
			payloadBuilder.launchImage(message.getLaunchImage());
		}
		if (message.isForNewsstand()) {
			payloadBuilder.forNewsstand();
		}
		if (message.isInstantDeliveryOrSilentNotification()) {
			payloadBuilder.instantDeliveryOrSilentNotification();
		}
		if (StringUtils.isNotBlank(message.getMdm())) {
			payloadBuilder.mdm(message.getMdm());
		}
		if (MapUtils.isNotEmpty(message.getCustomField())) {
			payloadBuilder.customFields(message.getCustomField());
		}
		String payload = payloadBuilder.build();
		ApnsNotification apnsNotification;
		try {
			apnsNotification = apnsService.push(message.getTarget(), payload);
			notification.setAttachment(apnsNotification);
			notification.setExpiresInSec(apnsNotification.getExpiry());
			notification.setIdentifier(String.valueOf(apnsNotification.getIdentifier()));
			notification.setMarshall(apnsNotification.marshall());
			notification.setSuccess(true);
			notification.setTarget(message.getTarget());
		} catch (Exception e) {
			notification.setSuccess(false);
			notification.setFailureMessage(ExceptionUtils.getStackTrace(e));
			log.error("Send IosMessage Error.", e);
		}
	}

	@Override
	protected void destroy0() throws Exception {
		super.destroy0();
		if (apnsService == null) {
			return;
		}
		apnsService.stop();
	}
	
	public void setApnsService(ApnsService apnsService) {
		this.apnsService = apnsService;
	}
	
	public void setProduction(boolean production) {
		this.production = production;
	}
	
	public void setAutoAdjustCacheLength(boolean autoAdjustCacheLength) {
		this.autoAdjustCacheLength = autoAdjustCacheLength;
	}
	
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setReconnectPolicy(ReconnectPolicy reconnectPolicy) {
		this.reconnectPolicy = reconnectPolicy;
	}
	
	public void setApnsDelegate(ApnsDelegate apnsDelegate) {
		this.apnsDelegate = apnsDelegate;
	}
}
