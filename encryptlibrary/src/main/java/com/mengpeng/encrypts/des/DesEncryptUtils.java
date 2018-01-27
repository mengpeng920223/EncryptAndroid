package com.mengpeng.encrypts.des;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * 作者：MengPeng
 * 时间：2018/1/27 上午10:10
 * 说明：
 */
public class DesEncryptUtils {
    public static String desDecode(String encodeResult, String des_password) {

        //获取要解密的字节数组数据
        byte[] data = Base64.decode(encodeResult, Base64.DEFAULT);
        if (des_password != null && des_password.length() == 8) {//判断密码是否符合条件
            if (data != null && data.length > 0) {//判断要解密的数据是否符合条件
                try {
                    //1、获取解密引擎
                    Cipher cipher = Cipher.getInstance("DES");
                    //2、对解密引擎进行初始化
                    SecretKeySpec key = new SecretKeySpec(des_password.getBytes(), "DES");
                    //参数一，DECRYPT_MODE代表着Cipher需要解密
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    //3、对数据进行解密
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

    /**
     * DES加密
     *
     * @param password 加密的8位密码
     * @param content  加密的内容
     * @return 加密后的内容
     */
    public static String desEncode(String password, String content) {
        String encodeContent = "";
        try {
            //cipher叫做加密引擎，通过工厂模式获取你想使用的加密方式
            Cipher cipher = Cipher.getInstance("DES");
            //然后对加密Cipher进行设置

            byte[] pdBytes = password.getBytes();
            if (pdBytes != null && pdBytes.length == 8) {
                SecretKeySpec key = new SecretKeySpec(pdBytes, "DES");
                //根据key初始化加密引擎cipher
                //Cipher.DECRYPT_MODE这种方式，解密
                //Cipher.ENCRYPT_MODE这种方式，加密
                cipher.init(Cipher.ENCRYPT_MODE, key);
                //进行完初始化操作，就可以使用此加密引擎
                //获取要加密的内容
                //转换成字节数组
                byte[] bytes = content.getBytes();

                if (bytes != null && bytes.length > 0) {
                    //doFinal就进行了加密操作
                    byte[] desEncodeResult = cipher.doFinal(bytes);
                    //将加密内容展示出来
                    //一般情况下，需要对加密的数据进行Base64转换，便于数据的传输
                    //进行Base64转换
                    byte[] encode = Base64.encode(desEncodeResult, Base64.DEFAULT);
                    //Base64转换之后，就可以显示数据了
                    encodeContent = new String(encode);
                } else {
                    encodeContent = "";
                    Log.d("des", "desEncode: 请输入要加密内容");
                }
            } else {
                encodeContent = "";
                Log.d("des", "desEncode: 请输入8位密码");
            }
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

        return encodeContent;
    }
}
