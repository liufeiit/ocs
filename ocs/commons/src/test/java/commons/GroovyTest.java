package commons;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月10日 下午2:57:19
 */
public class GroovyTest {
	
	public static void main(String[] args) {
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		binding.setVariable("name", "刘飞");
		String script = 
				"import groovy.sql.Sql;"
				+ "import commons.GroovyString;"
//				+ "db = Sql.newInstance(url:'jdbc:mysql://172.19.30.102:3306/af_cfc_db?useUnicode=true&amp;characterEncoding=UTF-8', user:'ac', password:'ac123', driverClassName:'com.mysql.jdbc.Driver');"
//				+ "db.query 'SELECT * FROM CFCMessageAuditRecord ORDER BY gmt_created DESC', {while(it.next()){println it.getString('appName')}};"
//				+ "db.rows('SELECT * FROM CFCMessageAuditRecord ORDER BY gmt_created DESC').each {row -> println 'row list : ' + row['appName']};"
//				+ "db.eachRow 'SELECT * FROM CFCMessageAuditRecord ORDER BY gmt_created DESC', {println 'eachRow : ' + it['appName']};"
//				+ "println 'firstRow : ' + db.firstRow('SELECT * FROM CFCMessageAuditRecord ORDER BY gmt_created DESC');"
				+ "println new GroovyString().welcome(name)";
		shell.evaluate(script);
	}

	public static void evalScriptAsMainMethod() {
		String[] args = new String[] { "Zhangsan", "10" };// main(String[] args)
		Binding binding = new Binding(args);
		GroovyShell shell = new GroovyShell(binding);
		shell.evaluate("static void main(String[] args){ if(args.length != 2) return;println('Hello,I am ' + args[0] + ',age ' + args[1])}");
		shell = null;
		binding = null;
	}

	public static void evalScript00() throws Exception {
		ScriptEngineManager factory = new ScriptEngineManager();
		// 每次生成一个engine实例
		ScriptEngine engine = factory.getEngineByName("groovy");
		System.out.println(engine.toString());
		assert engine != null;
		// javax.script.Bindings
		Bindings binding = engine.createBindings();
		binding.put("date", new Date());
		// 如果script文本来自文件,请首先获取文件内容
		engine.eval("def getTime(){return date.getTime();}", binding);
		engine.eval("def sayHello(name,age){return 'Hello,I am ' + name + ',age' + age;}");
		Long time = (Long) ((Invocable) engine).invokeFunction("getTime", null);
		System.out.println(time);
		String message = (String) ((Invocable) engine).invokeFunction("sayHello", "zhangsan", new Integer(12));
		System.out.println(message);
	}

	public static void main00(String[] args) throws Exception {
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		binding.setVariable("name", "zhangsan");
		shell.evaluate("println 'Hello World! I am ' + name;");
		// 在script中,声明变量,不能使用def,否则scrope不一致.
		shell.evaluate("date = new Date();");
		Date date = (Date) binding.getVariable("date");
		System.out.println("Date:" + date.getTime());
		// 以返回值的方式,获取script内部变量值,或者执行结果
		// 一个shell实例中,所有变量值,将会在此"session"中传递下去."date"可以在此后的script中获取
		Long time = (Long) shell.evaluate("def time = date.getTime(); return time;");
		System.out.println("Time:" + time);
		binding.setVariable("list", new String[] { "A", "B", "C" });
		// invoke method
		String joinString = (String) shell.evaluate("def call(){return list.join(' - ')};call();");
		System.out.println("Array join:" + joinString);
		shell = null;
		binding = null;

		evalScript00();

		String[] paths = { "C:\\dev\\groovy-embed\\src\\groovy\\com\\vanward\\groovy" };
		GroovyScriptEngine gse = new GroovyScriptEngine(paths);
		Binding binding0 = new Binding();
		Object[] path = { "C:\\music\\temp\\mp3s" };
		binding0.setVariable("args", path);
		gse.run("Songs.groovy", binding0);
		gse.run("BusinessObjects.groovy", binding0);
	}

	public static void evalScriptTextFull() throws Exception {
		StringBuffer buffer = new StringBuffer();
		// define API
		buffer.append("class User{").append("String name;Integer age;")
		// .append("User(String name,Integer age){this.name = name;this.age = age};")
				.append("String sayHello(){return 'Hello,I am ' + name + ',age ' + age;}}\n");
		// Usage
		buffer.append("def user = new User(name:'zhangsan',age:1);").append("user.sayHello();");
		// groovy.lang.Binding
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		String message = (String) shell.evaluate(buffer.toString());
		System.out.println(message);
		// 重写main方法,默认执行
		String mainMethod = "static void main(String[] args){def user = new User(name:'lisi',age:12);print(user.sayHello());}";
		shell.evaluate(mainMethod);
		shell = null;
	}

	public static void evalScript() throws Exception {
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		// 直接方法调用
		// shell.parse(new File(//))
		Script script = shell.parse("def join(String[] list) {return list.join('--');}");
		String joinString = (String) script.invokeMethod("join", new String[] { "A1", "B2", "C3" });
		System.out.println(joinString);
		// //脚本可以为任何格式,可以为main方法,也可以为普通方法
		// 1) def call(){...};call();
		// 2) call(){...};
		script = shell.parse("static void main(String[] args){i = i * 2;}");
		script.setProperty("i", new Integer(10));
		script.run();// 运行,
		System.out.println(script.getProperty("i"));
		// the same as
		System.out.println(script.getBinding().getVariable("i"));
		script = null;
		shell = null;
	}

	public static void parse() throws Exception {
		GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
		File sourceFile = new File("D:\\TestGroovy.groovy");
		Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
		GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();// proxy
		Long time = (Long) instance.invokeMethod("getTime", new Date());
		System.out.println(time);
		Date date = (Date) instance.invokeMethod("getDate", time);
		System.out.println(date.getTime());
		// here
		instance = null;
		testGroovyClass = null;
	}

	public static void load() throws Exception {
		GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\TestGroovy.class"));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for (;;) {
			int i = bis.read();
			if (i == -1) {
				break;
			}
			bos.write(i);
		}
		Class testGroovyClass = classLoader.defineClass(null, bos.toByteArray());
		// instance of proxy-class
		// if interface API is in the classpath,you can do such as:
		// MyObject instance = (MyObject)testGroovyClass.newInstance()
		GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
		Long time = (Long) instance.invokeMethod("getTime", new Date());
		System.out.println(time);
		Date date = (Date) instance.invokeMethod("getDate", time);
		System.out.println(date.getTime());

		// here
		bis.close();
		bos.close();
		instance = null;
		testGroovyClass = null;
	}
}