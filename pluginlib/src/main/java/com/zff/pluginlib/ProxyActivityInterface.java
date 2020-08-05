package com.zff.pluginlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public interface ProxyActivityInterface {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    boolean onTouchEvent(MotionEvent event);

    void onBackPressed();
}
