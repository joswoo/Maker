package com.example.qhdud.holo_final;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin,btJoin;
    private CheckBox cbIdSave;

    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Realm 초기화
        Realm.init(this);
        //configuration 설정(중간에 설정을 다르게 하는게 들어갈 수 있다)
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.bt_login_ok);
        btJoin = (Button)findViewById(R.id.bt_join_ok);
        cbIdSave = (CheckBox) findViewById(R.id.cb_login_save);

        realm = Realm.getDefaultInstance();


        //여기서 부터 로딩화면 holo가 들어가는 부분
        if(getIntent().getExtras()==null)
        {
            startActivity(new Intent(this, LoadActivity.class));
        }
        SharedPreferences preference = getSharedPreferences("a",MODE_PRIVATE); //처음 실행 여부를 결정
        int firstviewshow = preference.getInt("First",0);

        if(firstviewshow != 1) {
            startActivity(new Intent(this, FirstStartActivity.class));
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //사용자가 입력한 ID/Password가 DB에 있는지 비교체크 한다.
                RealmQuery<Member> query = realm.where(Member.class);
                //입력한 이메일과 패스워드와 일치하는 유저가 있는지 체크하고 가져 온다.
                //아이디/암호 기억하기 기능은 어떻게 구현하지? 폰캐시에 저장해야 하나?
                RealmResults<Member> user = query.equalTo("email", etEmail.getText().toString()).findAll();
                //결과가 있으면 로그인상태를 true로 변경 없으면 안내 메세지 출력
                if (user.size() == 0){
                    //다이어로그박스 출력 다시 시도하게 안내한다.
                    Toast.makeText(MainActivity.this, "일치하는 회원정보가 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    if(user.get(0).getPassword().equals(etPassword.getText().toString()))
                    { //스트링으로 만들어 줘야 비교체크가 되는군.
                        Intent intent = new Intent();
                        intent.putExtra("Name", user.get(0).getName().toString());
                        intent.putExtra("Email", user.get(0).getEmail().toString());

                        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("name", user.get(0).getName().toString());
                        editor.putString("email", etEmail.getText().toString());
                        //editor.putString("password", etPassword.getText().toString());
                        editor.commit();
                        //저장완료

                        setResult(Activity.RESULT_OK, intent);
                        startActivity(new Intent(getApplicationContext(),finishActivity.class));
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "암호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),JoinActivity.class));
            }
        });
    }



}