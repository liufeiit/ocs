package me.ocs.commons.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年6月23日 上午10:35:05
 */
public class CryptoUtils {
	/**
	 * 加密密钥 20****03 x 2 + 30****02 x 2
	 */
	private static final byte[] SECURITY_KEY = new byte[] { 32, 18, 9, 3, 32, 18, 9, 3, 48, -112, 33, 2, 48, -112, 33,
			2 };

	/**
	 * 加密数据
	 */
	public static final String encrypt(String data) {
		if (data == null) {
			return null;
		}
		
		byte[] encData = tdesEncrypt(data.getBytes(Charsets.UTF_8), SECURITY_KEY, true);
		return HexUtils.encodeHex(encData);
	}

	/**
	 * 解密数据
	 */
	static final String decrypt(String encData) {
		if (encData == null) {
			return null;
		}
		
		byte[] plainData = tdesDecrypt(HexUtils.decodeHex(encData), SECURITY_KEY, true);
		return new String(plainData, Charsets.UTF_8);
	}
	
	/**
	 * 3DES加密
	 * 
	 * @param msgInBCD
	 *            待加密数据
	 * @param keyInBCD
	 *            秘钥明文
	 * @param padding
	 *            是否使用填充模式
	 * @return 加密后的数据
	 */
	public static byte[] tdesEncrypt(byte[] msgInBCD, byte[] keyInBCD, boolean padding) {
		try {
			return initTdesCipher(keyInBCD, Cipher.ENCRYPT_MODE, padding).doFinal(msgInBCD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 3DES解密(padding为false)
	 * 
	 * @param msgInBCD
	 *            加密数据
	 * @param keyInBCD
	 *            秘钥明文
	 * @param padding
	 *            加密数据是否使用填充模式
	 * @return 解密后的数据
	 */
	public static byte[] tdesDecrypt(byte[] msgInBCD, byte[] keyInBCD, boolean padding) {
		try {
			return initTdesCipher(keyInBCD, Cipher.DECRYPT_MODE, padding).doFinal(msgInBCD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 初始化3DES秘钥
	 * 
	 * @param key
	 * @param mode
	 * @param padding
	 * @return
	 * @throws Exception
	 */
	private static Cipher initTdesCipher(byte[] key, int mode, boolean padding) throws Exception {
		Cipher cipher = null;
		if (padding) {
			cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");

		} else {
			cipher = Cipher.getInstance("DESede/ECB/NoPadding");
		}

		SecretKey secretKey = new SecretKeySpec(format3DesKey(key), "DESede");
		cipher.init(mode, secretKey);

		return cipher;
	}
	
	/**
	 * 格式化3DES秘钥
	 * 
	 * @param rawKey
	 * @return
	 */
	private static byte[] format3DesKey(byte[] rawKey) {
		if (rawKey.length == 8) {
			return ByteUtils.concat(rawKey, rawKey, rawKey);

		} else if (rawKey.length == 16) {
			return ByteUtils.concat(rawKey, ByteUtils.getBytes(rawKey, 0, 8));

		} else if (rawKey.length == 24) {
			return rawKey;

		} else {
			throw new RuntimeException("Invalid length of 3DES key, length=" + rawKey.length);
		}
	}

	private CryptoUtils() {
	}
}