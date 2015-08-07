package commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.AccessException;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年8月7日 下午3:55:22
 */
public class Expression {

	public static void main(String[] args) throws Throwable {
		Context c = new Context("fei.liu");
		c.setPassword("123456");
		StandardEvaluationContext context = new StandardEvaluationContext(c);
		SpelParserConfiguration config = new SpelParserConfiguration(true, true);
		ExpressionParser parser = new SpelExpressionParser(config);
		System.out.println(parser.parseExpression("'Hello World'.concat('!')").getValue());
		System.out.println(parser.parseExpression("'Hello World'.bytes.length").getValue());
		System.out.println(parser.parseExpression("new String('hello world').toUpperCase()").getValue(String.class));
		System.out.println(parser.parseExpression("name").getValue(context, String.class));
		System.out.println(parser.parseExpression("password").getValue(context, String.class));

		System.out.println(parser.parseExpression("6.0221415E+23").getValue());
		System.out.println(parser.parseExpression("0x7FFFFFFF").getValue());
		System.out.println(parser.parseExpression("true").getValue());
		System.out.println(parser.parseExpression("null").getValue());

		System.out.println(parser.parseExpression("{1, 2, 3, 4}").getValue(context));
		System.out.println(parser.parseExpression("{1, 2, 3, 4}").getValue(context));
		System.out.println(parser.parseExpression("{{'a', 'b'},{'x', 'y'}}").getValue(context));

		System.out.println(parser.parseExpression("new int[4]").getValue(context));

		System.out.println(parser.parseExpression("2 == 2").getValue(Boolean.class));

		System.out.println(parser.parseExpression("2 < -5.0").getValue(Boolean.class));
		System.out.println(parser.parseExpression("isMe('fei.liu0')").getValue(context, Boolean.class));

		System.out.println(parser.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class));
		System.out.println(parser.parseExpression("'5.00' matches '\\^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class));
		System.out.println(parser.parseExpression("'5.0067' matches '\\^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class));
		System.out.println(parser.parseExpression("true and false").getValue(Boolean.class));
		System.out.println(parser.parseExpression("isMe('fei') or isMe('fei.liu')").getValue(context, boolean.class));
		System.out.println(parser.parseExpression("1 - -3").getValue(Integer.class));

		parser.parseExpression("name").setValue(context, "Alexander Seovic2");
		System.out.println(parser.parseExpression("name").getValue(context, String.class));
		System.out.println(parser.parseExpression("name = 'Alexandar Seovic'").getValue(context, String.class));
		System.out.println(parser.parseExpression("T(java.util.Date)").getValue(Class.class));
		System.out.println(parser.parseExpression("T(String)").getValue(Class.class));
		System.out.println(parser.parseExpression("T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR").getValue(Boolean.class));

		List<Integer> primes = new ArrayList<Integer>();
		primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
		context.setVariable("primes", primes);
		System.out.println(parser.parseExpression("#primes.?[#this>10]").getValue(context));

		context.registerFunction("reverseString", StringUtil.class.getDeclaredMethod("reverseString", new Class[] { String.class }));
		String helloWorldReversed = parser.parseExpression("#reverseString('hello')").getValue(context, String.class);
		System.out.println(helloWorldReversed);

		context.setBeanResolver(new BeanResolver() {
			@Override
			public Object resolve(EvaluationContext context, String beanName) throws AccessException {
				return context.lookupVariable(beanName);
			}
		});
		Object bean = parser.parseExpression("@primes").getValue(context);
		System.out.println(bean);

		System.out.println(parser.parseExpression("false ? 'trueExp' : 'falseExp'").getValue(String.class));
		
		context.setVariable("queryName", "Nikola Tesla");

		String expression = "isMe(#queryName)? #queryName + ' is a member of the ' " +
		        "+ name + ' Society' : #queryName + ' is not a member of the ' + name + ' Society'";
		String queryResultString = parser.parseExpression(expression)
		        .getValue(context, String.class);
		System.out.println(queryResultString);
		
		
		
		
	}

	public abstract static class StringUtil {
		public static String reverseString(String input) {
			StringBuilder backwards = new StringBuilder();
			for (int i = 0; i < input.length(); i++)
				backwards.append(input.charAt(input.length() - 1 - i));
			return backwards.toString();
		}

	}

	public static class Context {
		private String name;
		private String password;

		public Context(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isMe(String name) {
			return StringUtils.equals(name, this.name);
		}
	}
}