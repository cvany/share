package com.fpx.pds.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 对称加解密工具
 *
 * @Author: cuiwy
 * @Date: 2019/7/8 16:28
 * @version: v1.0.0
 */
@Slf4j
public class AESUtil {
    /**
     * 默认密钥
     */
    private static String DEFAULT_KEY = "zjyDYF0doPvD0xTDtYUc3w==&";

    /**
     * 加密
     *
     * @param text
     * @return
     */
    public static String enCode(String text) {
        return enCode(null, text);
    }

    /**
     * 解密
     *
     * @param text
     */
    public static String deCode(String text) {
        return deCode(null, text);
    }

    /**
     * 加密
     *
     * @param key    密钥
     * @param encode 加密文本
     * @return
     */
    private static String enCode(String key, String encode) {
        if (!StringUtils.isBlank(key))
            DEFAULT_KEY = key;
        if (StringUtils.isBlank(encode))
            throw new RuntimeException("enCode must not be null");
        return encode(encode);
    }

    /**
     * 解密
     *
     * @param key    对称密钥
     * @param deCode 解密文本
     */
    private static String deCode(String key, String deCode) {
        if (!StringUtils.isBlank(key))
            DEFAULT_KEY = key;
        if (StringUtils.isBlank(deCode))
            throw new RuntimeException("deCode must not be null");
        return decode(deCode);
    }

    private static String encode(String encode) {
        try {
            byte[] bytes = encodeAndDecode(Cipher.ENCRYPT_MODE, encode.getBytes());
            return Hex.encodeHexString(bytes);
        } catch (Exception e) {
            log.error("encode method unknown error:{}", e);
        }
        return null;
    }

    private static String decode(String deCode) {
        try {
            byte[] decodeHex = Hex.decodeHex(deCode);
            byte[] result = encodeAndDecode(Cipher.DECRYPT_MODE, decodeHex);
            return new String(result);
        } catch (Exception e) {
            log.error("deCode method unknown error:{}", e);
        }
        return null;
    }

    private static byte[] encodeAndDecode(int mode, byte[] bytes)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(DEFAULT_KEY.getBytes());
        keyGenerator.init(random);
//        keyGenerator.init(new SecureRandom(DEFAULT_KEY.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] byteKey = secretKey.getEncoded();
        Key secret = new SecretKeySpec(byteKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, secret);
        return cipher.doFinal(bytes);
    }


}
