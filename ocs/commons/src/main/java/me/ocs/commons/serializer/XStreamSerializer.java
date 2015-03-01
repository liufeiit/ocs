package me.ocs.commons.serializer;

import java.io.Writer;
import java.lang.reflect.Type;

import com.thoughtworks.xstream.XStream;

/**
 * 使用XStream实现的序列化器.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年3月2日 上午12:10:01
 */
public class XStreamSerializer implements Serializer {
	
	private final XStream xstream;

	public XStreamSerializer() {
		super();
		xstream = new XStream();
	}

	@Override
	public void serialize(Object bean, Writer writer) {
		xstream.toXML(bean, writer);
	}

	@Override
	public void serialize(Type type, Object bean, Writer writer) {
		xstream.toXML(bean, writer);
	}

	@Override
	public String serialize(Object bean) {
		return xstream.toXML(bean);
	}

	@Override
	public String serialize(Type type, Object bean) {
		return xstream.toXML(bean);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, String data) {
		return clazz.cast(xstream.fromXML(data));
	}

	@Override
	public <T> T deserialize(Type type, String data) {
		return (T) xstream.fromXML(data);
	}
}