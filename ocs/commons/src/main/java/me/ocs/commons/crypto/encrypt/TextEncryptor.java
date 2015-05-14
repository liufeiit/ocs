package me.ocs.commons.crypto.encrypt;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午6:01:02
 */
public interface TextEncryptor {

    /**
     * Encrypt the raw text string.
     */
    String encrypt(String text);

    /**
     * Decrypt the encrypted text string.
     */
    String decrypt(String encryptedText);

}