package com.zff.chajianapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zff.pluginlib.PluginActivity;

import android.content.Intent;

public class ChaJianActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tvChajian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(that,ChaJian2Activity.class);
                startActivity(intent);
            }
        });
    }
}
