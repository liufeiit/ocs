package me.ocs.commons.crypto.encrypt;

import me.ocs.commons.crypto.codec.Hex;
import me.ocs.commons.crypto.codec.Utf8;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午6:01:28
 */
final class HexEncodingTextEncryptor implements TextEncryptor {

    private final BytesEncryptor encryptor;

    public HexEncodingTextEncryptor(BytesEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String encrypt(String text) {
        return new String(Hex.encode(encryptor.encrypt(Utf8.encode(text))));
    }

    public String decrypt(String encryptedText) {
        return Utf8.decode(encryptor.decrypt(Hex.decode(encryptedText)));
    }

}