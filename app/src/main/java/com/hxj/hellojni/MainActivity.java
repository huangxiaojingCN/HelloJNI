package com.hxj.hellojni;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView mTvMessage;
    private Button mBtnLoadLua;

    static {
        System.loadLibrary("luajava");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvMessage = findViewById(R.id.tv_message);
        mBtnLoadLua = findViewById(R.id.btn_load_lua);

        mBtnLoadLua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    }
                } else {
                    loadLuaFile();
                }
            }
        });
    }

    private void loadLuaFile() {
        // 创建 lua 栈
        LuaState luaState = LuaStateFactory.newLuaState();
        // 获取 lua 标准库
        luaState.openLibs();

        File file = new File(Environment.getExternalStorageDirectory(), "hello.lua");

        String luaFile = file.getAbsolutePath();

        int ret = luaState.LdoFile(luaFile);

        mTvMessage.setText("Lua 执行的结果: " + ret);

        luaState.close();
    }
}
