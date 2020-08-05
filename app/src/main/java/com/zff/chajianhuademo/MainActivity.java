package com.zff.chajianhuademo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zff.pluginlib.HookManager;
import com.zff.pluginlib.ProxyActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_chajian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 这里就是一个占坑的activity
                Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
                // 这里是拿到我们加载的插件的第一个activity的全类名
//                intent.putExtra("className", "com.zff.chajianapp.ChaJianActivity");
                intent.putExtra("className", HookManager.getInstance().packageInfo.activities[0].name);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_xiazai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HookManager.getInstance().loadPathToPlugin(MainActivity.this, "chajianapp-debug.apk");
                Toast.makeText(MainActivity.this,"下载插件成功",Toast.LENGTH_LONG).show();
            }
        });

    }
}

/**
 * 1、app打包后会形成dex、images、xml资源
 * 2、Dex靠PathClassLoader加载
 * 3、图片以及xml资源靠Resource加载
 **/