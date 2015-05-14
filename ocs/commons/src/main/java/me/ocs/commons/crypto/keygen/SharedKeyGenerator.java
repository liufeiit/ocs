package me.ocs.commons.crypto.keygen;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午5:54:23
 */
final class SharedKeyGenerator implements BytesKeyGenerator {

    private byte[] sharedKey;

    public SharedKeyGenerator(byte[] sharedKey) {
        this.sharedKey = sharedKey;
    }

    public int getKeyLength() {
        return sharedKey.length;
    }

    public byte[] generateKey() {
        return sharedKey;
    }

}