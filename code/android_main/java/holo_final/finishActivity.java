package com.example.qhdud.holo_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class finishActivity extends AppCompatActivity {

    Button btnMenu,btnLight,btnAlcol,btnRice;


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout frame,left_drawer;

    ArrayList<String> mDatas = new ArrayList<String>(); //tap menu에 들어갈 이름
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnLight = (Button) findViewById(R.id.btnLight);
        btnAlcol = (Button) findViewById(R.id.btnAlcol);
        btnRice = (Button) findViewById(R.id.btnRice);

        //drawer 연결
        mDrawerLayout = (DrawerLayout)findViewById(R.id.navigation_drawer);
        //drawer에서 오른쪽 클릭하면 나오는 레이아웃과 tap_menu list
        left_drawer= (RelativeLayout)findViewById(R.id.left_drawer);
        mDrawerList =(ListView)findViewById(R.id.tap_menu);


        mDatas.add("마이페이지");
        mDatas.add("보관함");
        mDatas.add("설정");
        mDatas.add("로그아웃");
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDatas);
        mDrawerList = (ListView)findViewById(R.id.tap_menu);
        mDrawerList.setAdapter(adapter);

        btnMenu.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!mDrawerLayout.isDrawerOpen(Gravity.LEFT))
                {

                    mDrawerLayout.openDrawer(Gravity.LEFT);

                }
            }
        });

        btnLight.setOnClickListener(new Button.OnClickListener()//혼밥남녀
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),LightActivity.class));
            }
        });
        btnAlcol.setOnClickListener(new Button.OnClickListener()//혼밥남녀
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),TrueActivity.class));
            }
        });

        btnRice.setOnClickListener(new Button.OnClickListener()//혼밥남녀
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),RiceActivity.class));
            }
        });

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int key = event.getKeyCode();
        if ( key== KeyEvent.KEYCODE_BACK) { // 백 버튼
            finish();
        }
        if((key==KeyEvent.KEYCODE_VOLUME_DOWN)){
            //Toast.makeText(ChatRoom.this, "외부 버튼 눌림", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(),popUpActivity.class);
//            startActivity(intent);

            Intent intent_ = new Intent(getApplicationContext(), popUpActivity.class);
            intent_.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 이거 안해주면 안됨
            getApplicationContext().startActivity(intent_);

        }return true;
    }

}



