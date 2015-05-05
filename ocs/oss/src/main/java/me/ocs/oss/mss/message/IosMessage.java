package me.ocs.oss.mss.message;

import java.util.HashMap;
import java.util.Map;

import me.ocs.oss.mss.Message;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * IOS设备消息.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午5:19:07
 */
public class IosMessage extends Message {
	
	public static final String MESSAGE_PROVIDER_NAME = "IOS";

	/**
	 * 消息执行的动作
	 */
	private String action;
	
	/**
	 * 消息包含的URL参数
	 */
	private String[] urlArgs;
	
	/**
	 * 消息声音
	 */
	private String sound;
	
	/**
	 * 消息类型
	 */
	private String category;
	
	/**
	 * 应用标示
	 */
	private int badge;
	
	/**
	 * 启动图片
	 */
	private String launchImage;
	
	private boolean forNewsstand = false;
	
	private boolean instantDeliveryOrSilentNotification = false;
	
	private String mdm;
	
	private Map<String, Object> customField;
	
	public IosMessage() {
		super(MESSAGE_PROVIDER_NAME);
		customField = new HashMap<String, Object>();
	}
	
	public IosMessage customField(final String key, final Object value) {
		if(StringUtils.isBlank(key) || value == null) {
			return this;
		}
		customField.put(key, value);
        return this;
    }
	
	public IosMessage customFields(final Map<String, ?> values) {
		if(MapUtils.isEmpty(values)) {
			return this;
		}
		customField.putAll(values);
        return this;
    }

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String[] getUrlArgs() {
		return urlArgs;
	}

	public void setUrlArgs(String[] urlArgs) {
		this.urlArgs = urlArgs;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBadge() {
		return badge;
	}

	public void setBadge(int badge) {
		this.badge = badge;
	}

	public String getLaunchImage() {
		return launchImage;
	}

	public void setLaunchImage(String launchImage) {
		this.launchImage = launchImage;
	}

	public boolean isForNewsstand() {
		return forNewsstand;
	}

	public void setForNewsstand(boolean forNewsstand) {
		this.forNewsstand = forNewsstand;
	}

	public boolean isInstantDeliveryOrSilentNotification() {
		return instantDeliveryOrSilentNotification;
	}

	public void setInstantDeliveryOrSilentNotification(boolean instantDeliveryOrSilentNotification) {
		this.instantDeliveryOrSilentNotification = instantDeliveryOrSilentNotification;
	}

	public String getMdm() {
		return mdm;
	}

	public void setMdm(String mdm) {
		this.mdm = mdm;
	}

	public Map<String, Object> getCustomField() {
		return customField;
	}

	public void setCustomField(Map<String, Object> customField) {
		this.customField = customField;
	}
}