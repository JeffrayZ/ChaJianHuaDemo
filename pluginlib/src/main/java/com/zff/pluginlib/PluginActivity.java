package com.zff.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

import androidx.annotation.NonNull;

public class PluginActivity extends Activity implements ProxyActivityInterface {
    /**
     * 这里的that 指的是我们的宿主app，因为插件是没有安装的 是没有上下文的
     */
    public Activity that;
    private int mFrom = FROM_INTERNAL;

    @Override
    public void attach(Activity activity) {
        that = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mFrom = savedInstanceState.getInt("FROM");
        }
        if (mFrom == FROM_INTERNAL) {
            super.onCreate(savedInstanceState);
            that = this;
        }
    }

    @Override
    public void onStart() {
        if (mFrom == FROM_INTERNAL) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (mFrom == FROM_INTERNAL) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mFrom == FROM_INTERNAL) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mFrom == FROM_INTERNAL) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (mFrom == FROM_INTERNAL) {
            super.onDestroy();
        }
    }

    public int getmFrom() {
        return mFrom;
    }

    @Override
    public void setContentView(View view) {
        if (mFrom == FROM_INTERNAL) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) { //最终调用宿主的activity
        if (mFrom == FROM_INTERNAL) {
            super.setContentView(layoutResID);
        } else {
            that.setContentView(layoutResID);
        }
    }

    @Override
    public void onRestart() {
        if (mFrom == FROM_INTERNAL) {
            super.onRestart();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mFrom == FROM_INTERNAL) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (mFrom == FROM_INTERNAL) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public Intent getIntent() {
        if (mFrom == FROM_INTERNAL) {
            return super.getIntent();
        } else {
            return that.getIntent();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (mFrom == FROM_INTERNAL) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (mFrom == FROM_INTERNAL) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (mFrom == FROM_INTERNAL) {
            return super.getApplicationInfo();
        } else {
            return that.getApplicationInfo();
        }
    }

    @Override
    public Window getWindow() {
        if (mFrom == FROM_INTERNAL) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (mFrom == FROM_INTERNAL) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @Override
    public void startActivity(Intent intent) {
//        ProxyActivity --->className
        Intent m = new Intent();
        m.putExtra("className", intent.getComponent().getClassName());
        that.startActivity(m);
    }
}
