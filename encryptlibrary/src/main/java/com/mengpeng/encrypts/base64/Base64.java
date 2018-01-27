package com.mengpeng.encrypts.base64;

import android.os.Environment;
import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 创建:  MengPeng
 * 日期:  2017/8/17 , 下午2:54.
 * 作用:  base64加密、解密
 */
public class Base64 {
    /**
     * 图片转化成base64字符串
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param filePath 图片路径
     * @return 加密后的字符串
     */
    public static String picToString(String filePath) {

        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    /**
     * base64字符串转化成图片
     * 对字节数组字符串进行Base64解码并生成图片
     */
    public static String stringToPic(String imgStr, String filepath, String imgName) {

        BASE64Decoder decoder = new BASE64Decoder();

        if (imgStr == null) //图像数据为空
            return "";
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            String imgFilePath;
            if (TextUtils.isEmpty(filepath)) {
                imgFilePath = Environment.
                        getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DCIM) + imgName;//新生成的图片
            } else {
                imgFilePath = filepath + imgName;//新生成的图片
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return imgFilePath;

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 加密
     */
    public static String encode(String content) {
        if (content != null) {
            String encode = android.util.Base64.encodeToString(content.getBytes(),
                    android.util.Base64.DEFAULT).replace("\n", "");
            return encode;
        }
        return null;
    }

    /**
     * 解密
     */
    public static String decode(String content) {
        if (content != null) {
            byte[] decode = android.util.Base64.decode(content,
                    android.util.Base64.DEFAULT);
            return new String(decode);
        }
        return null;
    }
}
