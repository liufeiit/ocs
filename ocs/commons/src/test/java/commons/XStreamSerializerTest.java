package commons;

import me.ocs.commons.serializer.Serializer;
import me.ocs.commons.serializer.XStreamSerializer;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年3月2日 上午12:37:25
 */
public class XStreamSerializerTest {
	@XStreamAlias("Std")
	public static class Std {
		@XStreamAlias("NAME")
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static void main(String[] args) {
		Serializer serializer = new XStreamSerializer();
		Std std = new Std();
		std.setName("fei.liu");
		System.out.println(serializer.serialize(std));
		System.out.println(((Std)serializer.deserialize(Std.class.getGenericSuperclass(), serializer.serialize(std))).getName());
	}
}