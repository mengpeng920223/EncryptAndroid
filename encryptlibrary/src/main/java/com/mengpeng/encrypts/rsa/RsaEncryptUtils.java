package com.mengpeng.encrypts.rsa;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 作者：MengPeng
 * 时间：2018/1/26 下午2:43
 * 说明：Rsa加密
 */
public class RsaEncryptUtils {

    /**
     * 获取KeyPair
     */
    public static KeyPair createRsaPassword() {
        KeyPair keyPair = null;
        try {
            //1、获取keyPairGenerator,通过工厂模式获取
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            //2、进行初始化
            //初始化时传递的keySize长度必须是1024位以上
            //定义了密码的长度了
            kpg.initialize(1024);
            //3、初始化后就可以获取KeyPair（公钥私钥对儿）
            keyPair = kpg.generateKeyPair();
            //4、分别获取公钥私钥
            // 获取私钥
//            aPrivate = keyPair.getPrivate();
            // 获取公钥
//            aPublic = keyPair.getPublic();

            //密码很长，需要存储，存储SharePreference或者File中


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return keyPair;
    }


    public static String rsaEncode(String content, PrivateKey aPrivate) {
        byte[] data = content.getBytes();
        if (aPrivate != null) {
            if (data != null && data.length > 0) {
                try {
                    //1、加密引擎
                    Cipher cipher = Cipher.getInstance("RSA");
                    //2、对Cipher进行初始化
                    cipher.init(Cipher.ENCRYPT_MODE, aPrivate);
                    //3、加密数据
                    return new String(Base64.encode(cipher.doFinal(data), Base64.NO_WRAP));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static String rsaDecode(String content, PublicKey aPublic) {
        //获取要解密的数据
        byte[] data = Base64.decode(content, Base64.NO_WRAP);

        if (aPublic != null) {
            if (data != null && data.length > 0) {
                try {
                    //1、获取Cipher引擎
                    Cipher cipher = Cipher.getInstance("RSA");

                    //2、初始化cipher
                    cipher.init(Cipher.DECRYPT_MODE, aPublic);

                    //3、解密数据
                    return new String(cipher.doFinal(data));

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }
}
