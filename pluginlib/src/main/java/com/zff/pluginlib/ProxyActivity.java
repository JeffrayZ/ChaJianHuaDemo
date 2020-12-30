package com.zff.pluginlib;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;

public class ProxyActivity extends Activity {
    private ProxyActivityInterface pluginObj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在这里拿到了真实跳转的activity 拿出来 再去启动真实的activity
        String className = getIntent().getStringExtra("className");

        //通过反射在去启动一个真实的activity 拿到Class对象
        try {
            Class<?> plugClass = getClassLoader().loadClass(className);
            Constructor<?> pluginConstructor = plugClass.getConstructor(new Class[]{});
            // 因为插件的activity实现了我们的标准
            pluginObj = (ProxyActivityInterface) pluginConstructor.newInstance(new Object[]{});
            // 注入上下文
            pluginObj.attach(this);
            // 一定要调用onCreate
            Bundle bundle = new Bundle();
            bundle.putInt("FROM",ProxyActivityInterface.FROM_EXTERNAL);
            pluginObj.onCreate(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 重写classLoader
    @Override
    public ClassLoader getClassLoader() {
        return HookManager.getInstance().getClassLoader();
    }

    // 重写Resource
    @Override
    public Resources getResources() {
        return HookManager.getInstance().getResource();
    }

    @Override
    public Context createConfigurationContext(Configuration overrideConfiguration) {
        return super.createConfigurationContext(HookManager.getInstance().getConfiguration());
    }


    @Override
    public AssetManager getAssets() {
        return HookManager.getInstance().getAssertManagerObj();
    }

    @Override
    public void startActivity(Intent intent) {
        String className1 = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className", className1);
        super.startActivity(intent1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        pluginObj.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pluginObj.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pluginObj.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pluginObj.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pluginObj.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pluginObj.onRestart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        pluginObj.onSaveInstanceState(outState);
    }
}
