package me.ocs.commons.crypto.encrypt;

import me.ocs.commons.crypto.keygen.KeyGenerators;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午6:06:05
 */
public class Encryptors {

    /**
     * Creates a standard password-based bytes encryptor using 256 bit AES encryption.
     * Derives the secret key using PKCS #5's PBKDF2 (Password-Based Key Derivation Function #2).
     * Salts the password to prevent dictionary attacks against the key.
     * The provided salt is expected to be hex-encoded; it should be random and at least 8 bytes in length.
     * Also applies a random 16 byte initialization vector to ensure each encrypted message will be unique.
     * Requires Java 6.
     *
     * @param password the password used to generate the encryptor's secret key; should not be shared
     * @param salt a hex-encoded, random, site-global salt value to use to generate the key
     */
    public static BytesEncryptor standard(CharSequence password, CharSequence salt) {
        return new AesBytesEncryptor(password.toString(), salt, KeyGenerators.secureRandom(16));
    }

    /**
     * Creates a text encryptor that uses standard password-based encryption.
     * Encrypted text is hex-encoded.
     *
     * @param password the password used to generate the encryptor's secret key; should not be shared
     */
    public static TextEncryptor text(CharSequence password, CharSequence salt) {
        return new HexEncodingTextEncryptor(standard(password, salt));
    }

    /**
     * Creates an encryptor for queryable text strings that uses standard password-based encryption.
     * Uses a 16-byte all-zero initialization vector so encrypting the same data results in the same encryption result.
     * This is done to allow encrypted data to be queried against.
     * Encrypted text is hex-encoded.
     *
     * @param password the password used to generate the encryptor's secret key; should not be shared
     * @param salt a hex-encoded, random, site-global salt value to use to generate the secret key
     */
    public static TextEncryptor queryableText(CharSequence password, CharSequence salt) {
        return new HexEncodingTextEncryptor(new AesBytesEncryptor(password.toString(), salt));
    }

    /**
     * Creates a text encryptor that performs no encryption.
     * Useful for developer testing environments where working with plain text strings is desired for simplicity.
     */
    public static TextEncryptor noOpText() {
        return NO_OP_TEXT_INSTANCE;
    }

    private Encryptors() {
    }

    private static final TextEncryptor NO_OP_TEXT_INSTANCE = new NoOpTextEncryptor();

    private static final class NoOpTextEncryptor implements TextEncryptor {

        public String encrypt(String text) {
            return text;
        }

        public String decrypt(String encryptedText) {
            return encryptedText;
        }

    }

}