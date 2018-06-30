package com.example.qhdud.holo_final;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class popUpActivity extends Activity {

    Button btn_ok,btn_cancle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_pop_up);

        btn_cancle = (Button)findViewById(R.id.btn_cancel);
        btn_ok = (Button)findViewById(R.id.btn_ok);

        btn_cancle.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_ok.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                //음악 화면으로
                setContentView(R.layout.activity_music);
            }
        });
    }
}
