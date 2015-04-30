package me.ocs.oss.message;

/**
 * 消息发送服务.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月30日 下午3:43:51
 */
public interface MessageService {
	
	/**
	 * 注册到一个消息提供商
	 */
	void registProvider(MessageProvider provider);

	/**
	 * 执行消息发送
	 */
	MessageNotification send(Message message) throws MessageException;
}