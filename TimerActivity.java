package com.example.qhdud.holo_final;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import io.realm.Realm;

public class TimerActivity extends AppCompatActivity {

    Realm realm;
    Button btnChoice;
    NumberPicker TimerSet;
    String userName;
    String resetTimer = "1";//default로 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        userName = preferences.getString("name", "");
        realm.init(getApplicationContext());//추가
        realm = Realm.getDefaultInstance();

        btnChoice = (Button) findViewById(R.id.btnChoice);
        TimerSet = (NumberPicker) findViewById(R.id.TimerSet);

        TimerSet.setMinValue(1);
        TimerSet.setMaxValue(10);
        TimerSet.setWrapSelectorWheel(true);
        TimerSet.setDescendantFocusability(TimerSet.FOCUS_BLOCK_DESCENDANTS);
        Log.i("timer before",resetTimer);
        TimerSet.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
            }
        });

        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼이 눌렸을때 동작 설정
                realm.beginTransaction();
                Member member = realm.where(Member.class).equalTo("name", userName).findFirst();

                resetTimer = String.valueOf(TimerSet.getValue());
                member.setTime(resetTimer);
//                SharedPreferences timer_prefs = getSharedPreferences("timer_prefs", MODE_PRIVATE);
//                SharedPreferences.Editor timerEditor = timer_prefs.edit();
//                timerEditor.putString("timer", resetTimer);
                Log.i("timer",resetTimer);
                realm.commitTransaction();
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //정리하기
        realm.removeAllChangeListeners();
        realm.close();
    }
}
