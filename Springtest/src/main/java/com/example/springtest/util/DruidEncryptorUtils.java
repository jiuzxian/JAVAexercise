package com.example.springtest.util;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.SneakyThrows;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * alibaba druid加解密规则：
 * 明文密码+私钥(privateKey)加密=加密密码
 * 加密密码+公钥(publicKey)解密=明文密码
 */
public final class DruidEncryptorUtils {

    private static String privateKey;

    private static String publicKey;

    private static void generateKeyPair() {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            publicKey = keyPair[1];
            System.out.println("privateKey-->" + privateKey);
            System.out.println("publicKey-->" + publicKey);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        generateKeyPair();
        // 演示加密和解密
        String originalPassword = "e@8y-eEG";
        String encryptedPassword = encode(originalPassword);
        String decryptedPassword = decode(encryptedPassword);
    }





    /**
     * 明文加密
     *
     * @param plaintext
     * @return
     */
    @SneakyThrows
    public static String encode(String plaintext) {
        System.out.println("明文字符串：" + plaintext);
        String ciphertext = ConfigTools.encrypt(privateKey, plaintext);
        System.out.println("加密后字符串：" + ciphertext);
        return ciphertext;
    }

    /**
     * 解密
     *
     * @param ciphertext
     * @return
     */
    @SneakyThrows
    public static String decode(String ciphertext) {
        System.out.println("加密字符串：" + ciphertext);
        String plaintext = ConfigTools.decrypt(publicKey, ciphertext);
        System.out.println("解密后的字符串：" + plaintext);

        return plaintext;
    }


}
