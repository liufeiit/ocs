package me.ocs.oauth.session;

/**
 * Session接口定义类。
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月3日 下午7:15:05
 */
public interface Session {
	/**
	 * 获得属性
	 * 
	 * @param name
	 * @return
	 */
	Object getAttribute(String name);

	/**
	 * 设置属性
	 * 
	 * @param name
	 * @param value
	 */
	void setAttribute(String name, Object value);

	/**
	 * 删除属性
	 * 
	 * @param name
	 */
	void removeAttribute(java.lang.String name);

	/**
	 * 获得建立时间
	 * 
	 * @return
	 */
	long getCreationTime();

	/**
	 * 获得SessionId
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 获得最近的访问时间
	 * 
	 * @return
	 */
	long getLastAccessedTime();

	/**
	 * 获得最大不活动间隔时间（秒）
	 * 
	 * @return
	 */
	int getMaxInactiveInterval();

	/**
	 * 设置最大不活动间隔时间（秒）
	 * 
	 * @param interval
	 */
	void setMaxInactiveInterval(int interval);

	/**
	 * 会话是否有效
	 * 
	 * @return
	 */
	boolean isValid();

	/**
	 * 失效会话
	 */
	void invalidate();
}