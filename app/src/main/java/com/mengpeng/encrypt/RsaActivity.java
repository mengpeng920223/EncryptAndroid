package com.mengpeng.encrypt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mengpeng.encrypt.R;
import com.mengpeng.encrypts.rsa.RsaEncryptUtils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaActivity extends AppCompatActivity {

    private PrivateKey aPrivate;
    private PublicKey aPublic;
    private TextView show_private_key;
    private TextView show_content;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_private_key = findViewById(R.id.show_private_key);
        et_content = findViewById(R.id.content);
        show_content = findViewById(R.id.show_content);

        btnCreatePassword();

    }

    //生成公钥私钥
    public void btnCreatePassword() {

        KeyPair keyPair = RsaEncryptUtils.createRsaPassword();

        //4、分别获取公钥私钥
        // 获取私钥
        aPrivate = keyPair.getPrivate();
        // 获取公钥
        aPublic = keyPair.getPublic();

        //密码很长，需要存储，存储SharePreference或者File中
        //存入文件或者SharePreference中的公钥私钥如何提取出来？？？

    }

    //RSA加密
    public void btnRSAEncode(View view) {
        String content = et_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "加密内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //一般情况下采用私钥进行加密
        String encode_result = RsaEncryptUtils.rsaEncode(content, aPrivate);

        if (TextUtils.isEmpty(encode_result)) {
            show_private_key.setText("RSA加密成功:\n" + encode_result);
        } else {
            show_private_key.setText("RSA加密失败\n");
        }
    }

    public void btnRSADecode(View view) {

        String str = show_private_key.getText().toString().replace("RSA加密成功:\n", "");

        String decode_result = RsaEncryptUtils.rsaDecode(str, aPublic);

        if (TextUtils.isEmpty(decode_result)) {
            show_content.setText("RSA解密成功:\n" + decode_result);
        } else {
            show_content.setText("RSA解密失败\n");
        }
    }
}
