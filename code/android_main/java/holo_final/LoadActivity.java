package com.example.qhdud.holo_final;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Handler hd = new Handler();
        hd.postDelayed(new Runnable(){
            @Override
            public void run()
            {
                finish();
            }
        },3000);//3초동안 보이고 사라지게
    }
}
