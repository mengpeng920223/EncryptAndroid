package com.mengpeng.encrypts.sha1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：MengPeng
 * 时间：2018/1/27 - 下午1:50
 * 说明：Sha1加密
 */
public class Sha1EncryptUtils {

    public static String sha1Encode(String content) {
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest msgDitest = MessageDigest.getInstance("SHA-1");
            msgDitest.update(content.getBytes());
            byte[] digests = msgDitest.digest();
            //将每个字节转为16进制
            for (int i = 0; i < digests.length; i++) {
                builder.append(Integer.toHexString(digests[i] & 0xff + 8));//+8为加盐操作
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
