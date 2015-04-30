package me.ocs.oss.message;

/**
 * 消息发送供应商.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午4:54:41
 */
public interface MessageProvider<M extends Message> {
	
	/**
	 * 供应商名称
	 */
	String getName();
	
	/**
	 * 消息消费
	 */
	void consume(M message, MessageNotification notification) throws Exception;
}