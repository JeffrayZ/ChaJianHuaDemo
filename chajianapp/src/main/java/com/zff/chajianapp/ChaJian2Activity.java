package com.zff.chajianapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zff.pluginlib.PluginActivity;

public class ChaJian2Activity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.tvChajian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(that, v.toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(that,SecondActivity.class);
//                startActivity(intent);
            }
        });
    }
}
