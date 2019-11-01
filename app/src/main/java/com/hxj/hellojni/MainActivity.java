package com.hxj.hellojni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTvMessage;

    static {
        System.loadLibrary("hello-jni");
    }

    public native String messageFromJNI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvMessage = findViewById(R.id.tv_message);
        mTvMessage.setText("来自JNI的消息: " + messageFromJNI());
    }
}
