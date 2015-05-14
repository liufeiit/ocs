package commons;

import me.ocs.commons.crypto.bcrypt.BCryptPasswordEncoder;
import me.ocs.commons.crypto.password.PasswordEncoder;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午9:45:43
 */
public class EncryptorsTest {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("liufei123456"));
		System.out.println(passwordEncoder.matches("liufei123456", passwordEncoder.encode("liufei123456")));
	}
}