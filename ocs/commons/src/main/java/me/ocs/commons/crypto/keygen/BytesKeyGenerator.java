package me.ocs.commons.crypto.keygen;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午5:52:19
 */
public interface BytesKeyGenerator {

    /**
     * Get the length, in bytes, of keys created by this generator.
     * Most unique keys are at least 8 bytes in length.
     */
    int getKeyLength();

    /**
     * Generate a new key.
     */
    byte[] generateKey();

}