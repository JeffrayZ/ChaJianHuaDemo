package com.zff.pluginlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class PluginActivity extends Activity implements ProxyActivityInterface {
    /**
     * 这里的that 指的是我们的宿主app，因为插件是没有安装的 是没有上下文的
     */
    public Activity mProxyActivity;
    private int mFrom = FROM_INTERNAL;

    @Override
    public void attach(Activity activity) {
        mProxyActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mFrom = savedInstanceState.getInt("FROM");
        }
        if (mFrom == FROM_INTERNAL) {
            super.onCreate(savedInstanceState);
            mProxyActivity = this;
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
    public void setContentView(View view) {//最终调用宿主的activity
        if (mFrom == FROM_INTERNAL) {
            super.setContentView(view);
        }
        mProxyActivity.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mFrom == FROM_INTERNAL) {
            super.setContentView(layoutResID);
        }
        mProxyActivity.setContentView(layoutResID);
    }

//    @Override
//    public View findViewById(int id) {
//        return mProxyActivity.findViewById(id);
//    }

//    @Override
//    public Intent getIntent() {
//        if (mProxyActivity != null) {
//            return mProxyActivity.getIntent();
//        }
//        return super.getIntent();
//    }

//    @Override
//    public ClassLoader getClassLoader() {
//        return mProxyActivity.getClassLoader();
//    }

//    @NonNull
//    @Override
//    public LayoutInflater getLayoutInflater() {
//        return mProxyActivity.getLayoutInflater();
//    }

//    @Override
//    public ApplicationInfo getApplicationInfo() {
//        return mProxyActivity.getApplicationInfo();
//    }

//    @Override
//    public Window getWindow() {
//        return mProxyActivity.getWindow();
//    }


//    @Override
//    public WindowManager getWindowManager() {
//        return mProxyActivity.getWindowManager();
//    }

//    @Override
//    public void startActivity(Intent intent) {
//        Intent m = new Intent();
//        m.putExtra("ClassName", intent.getComponent().getClassName());
//        mProxyActivity.startActivity(m);
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
