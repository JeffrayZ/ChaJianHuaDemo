package com.zff.pluginlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public interface ProxyActivityInterface {
    // 系统安装
    int FROM_INTERNAL = 0;
    // 插件化启动
    int FROM_EXTERNAL = 1;

    void attach(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onRestart();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    boolean onTouchEvent(MotionEvent event);

    void onBackPressed();
}
