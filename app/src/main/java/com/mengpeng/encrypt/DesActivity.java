package com.mengpeng.encrypt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mengpeng.encrypts.des.DesEncryptUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class DesActivity extends AppCompatActivity {

    private EditText input;
    private EditText password;
    private TextView show_desEncode_result;
    private TextView show_desDecode_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des);
        initView();
    }


    private void initView() {
        //输入要加密的内容
        input = ((EditText) findViewById(R.id.des_input));
        //加密所需要的密码
        password = ((EditText) findViewById(R.id.des_password));
        //显示加密TextView
        show_desEncode_result = ((TextView) findViewById(R.id.show_desEncode_result));
        //显示解密TextView
        show_desDecode_result = ((TextView) findViewById(R.id.show_desDecode_result));
    }

    /**
     * 使用DES进行加密
     *
     * @param view
     */
    public void btnDESEncode(View view) {
        String passwords = password.getText().toString();
        String content = input.getText().toString();
        //自己定义的加密的密码，需要加密的内容
        String encode = DesEncryptUtils.desEncode(passwords, content);

        show_desEncode_result.setText(encode);
    }

    /**
     * 使用DES进行解密
     *
     * @param view
     */
    public void btnDESDecode(View view) {
        String decode = show_desEncode_result.getText().toString();
        String passwords = password.getText().toString();

        //加密后的内容 ， 加密时自定义的密码
        String des_result = DesEncryptUtils.desDecode(decode, passwords);
        //展示解密数据
        show_desDecode_result.setText(des_result);
    }
}
