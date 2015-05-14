package me.ocs.commons.crypto.encrypt;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午6:02:58
 */
public interface BytesEncryptor {

    /**
     * Encrypt the byte array.
     */
    byte[] encrypt(byte[] byteArray);

    /**
     * Decrypt the byte array.
     */
    byte[] decrypt(byte[] encryptedByteArray);

}