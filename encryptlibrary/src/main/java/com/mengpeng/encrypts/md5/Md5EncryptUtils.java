package com.mengpeng.encrypts.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：MengPeng
 * 时间：2018/1/27 - 下午1:16
 * 说明：MD5加密
 */
public class Md5EncryptUtils {

    public static String md5Encode(String content) {
        String encode = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(content.getBytes());
            StringBuffer buffer = new StringBuffer();
            for (byte b : result) {
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            encode = buffer.toString() ;
            return encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }
}
