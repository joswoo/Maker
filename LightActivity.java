package com.example.qhdud.holo_final;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LightActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference SWITCH;
    DatabaseReference Time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
         /*if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true); // 다른 인스턴스보다 먼저 실행되어야 한다.
            calledAlready = true;
        }*/
        database = FirebaseDatabase.getInstance();
        SWITCH = database.getReference("SWITCH");
        Time = database.getReference("AUTO");

        final Switch LedSwitch = (Switch) findViewById(R.id.led_switch);
        Button AutoButton = (Button) findViewById(R.id.button_auto);
        final NumberPicker NumPick1 = (NumberPicker) findViewById(R.id.numpicker1);
        final NumberPicker NumPick2 = (NumberPicker) findViewById(R.id.numpicker2);
        NumPick1.setMinValue(1);
        NumPick2.setMinValue(1);
        NumPick1.setMaxValue(24);
        NumPick2.setMaxValue(24);
        NumPick1.setWrapSelectorWheel(true);
        NumPick2.setWrapSelectorWheel(true);
        NumPick1.setDescendantFocusability(NumPick1.FOCUS_BLOCK_DESCENDANTS);
        NumPick2.setDescendantFocusability(NumPick2.FOCUS_BLOCK_DESCENDANTS);

        Time.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                TextView timeView1 = (TextView) findViewById(R.id.setTime1);
                TextView timeView2 = (TextView) findViewById(R.id.setTime2);
                int value_time1 = Integer.parseInt(dataSnapshot.child("time1").getValue(String.class));
                int value_time2 = Integer.parseInt(dataSnapshot.child("time2").getValue(String.class));
                timeView1.setText(value_time1 + ":00");
                timeView2.setText(value_time2 + ":00");
                NumPick1.setValue(value_time1);
                NumPick2.setValue(value_time2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        NumPick1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
            }
        });

        NumPick2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Display the newly selected number from picker
                // Time.child("time2").setValue(newVal);
            }
        });


        LedSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LedSwitch.isChecked()) {

                    SWITCH.setValue("0");
                } else {

                    SWITCH.setValue("1");
                }
            }
        });



        AutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼이 눌렸을때 동작 설정

                SWITCH.setValue("2");
                String numpick1 = String.valueOf(NumPick1.getValue());
                String numpick2 = String.valueOf(NumPick2.getValue());
                Time.child("time1").setValue(numpick1);
                Time.child("time2").setValue(numpick2);

            }
        });
    }

}
