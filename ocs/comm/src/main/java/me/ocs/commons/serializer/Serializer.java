package me.ocs.commons.serializer;

import java.io.Writer;
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

	void serialize(Object bean, Writer writer);

	void serialize(Type type, Object bean, Writer writer);

	String serialize(Object bean);

	String serialize(Type type, Object bean);

	<T> T deserialize(Class<T> clazz, String data);
}