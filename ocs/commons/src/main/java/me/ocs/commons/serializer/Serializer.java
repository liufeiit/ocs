package me.ocs.commons.serializer;

import java.lang.reflect.Type;

/**
 * 表示一个序列化器.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月28日 下午6:46:11
 */
public interface Serializer {

	byte[] serialize(Object bean);

	byte[] serialize(Type type, Object bean);

	void serialize(Object bean, Appendable writer);

	void serialize(Type type, Object bean, Appendable writer);

	String serializeAsString(Object bean);

	String serializeAsString(Type type, Object bean);

	<T> T deserialize(Class<T> clazz, byte[] data);

	Object deserialize(Type type, byte[] data);

	<T> T deserialize(Class<T> clazz, String data);

	Object deserialize(Type type, String data);
}