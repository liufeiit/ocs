package me.ocs.commons.crypto.keygen;

import me.ocs.commons.crypto.codec.Hex;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午5:53:02
 */
final class HexEncodingStringKeyGenerator implements StringKeyGenerator {

    private final BytesKeyGenerator keyGenerator;

    public HexEncodingStringKeyGenerator(BytesKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public String generateKey() {
        return new String(Hex.encode(keyGenerator.generateKey()));
    }

}