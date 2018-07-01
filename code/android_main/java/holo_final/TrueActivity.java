package com.example.qhdud.holo_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TrueActivity extends AppCompatActivity {


    Button btnBack,btnStart,btnSet,btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnSet = (Button) findViewById(R.id.btnSet);
        btnShow=(Button) findViewById(R.id.btnShow);
        //뒤의 메뉴로 돌아가기 -> finish activity
        btnBack.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //취중진담 보여주기
        btnShow.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WriteAllActivity.class));
            }
        });
        //취중진담 시작하기
        btnStart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WriteActivity.class));
            }
        });
        //사라지는 timer 설정
        btnSet.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TimerActivity.class));
            }
        });
    }
}
