package com.mengpeng.encrypt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mengpeng.encrypts.md5.Md5EncryptUtils;

public class Md5Activity extends AppCompatActivity {

    public static final String TAG = "Md5Activity" ;

    private EditText md5EncodeEt;
    private Button md5EncodeBtn;
    private TextView md5EncodeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5);

        md5EncodeEt = findViewById(R.id.md5EncodeEt);
        md5EncodeBtn = findViewById(R.id.md5EncodeBtn);
        md5EncodeContent = findViewById(R.id.md5EncodeContent);


        md5EncodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = md5EncodeEt.getText().toString();
                String encode = Md5EncryptUtils.md5Encode(content);
                md5EncodeContent.setText(encode);
                Log.d(TAG, "onClick: encode0："+encode);
                Log.d(TAG, "onClick: encode1："+"c1f32059ed6c218c4ad9b04385ff912b");
                Log.d(TAG, "onClick: encode："+encode.length());
            }
        });
    }
}
