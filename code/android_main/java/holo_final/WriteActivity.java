package com.example.qhdud.holo_final;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class WriteActivity extends AppCompatActivity{

    Button btnVoice, btnFinish,btnHome;
    Realm realm;
    EditText writeText;
    String userName;
    String fileName;
    Member member;

    private RealmResults<Member> myDataset;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private RealmQuery<Member> query;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        userName = preferences.getString("name", "");

        writeText = (EditText) findViewById(R.id.writeText);
        btnVoice = (Button) findViewById(R.id.btnVoice);
        btnFinish = (Button) findViewById(R.id.btnFinish);
        btnHome = (Button)findViewById(R.id.btnhome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),finishActivity.class));
            }
        });
        btnVoice.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int Num = 1;
                        try {
                            realm.init(getApplicationContext());
                            realm = Realm.getDefaultInstance();

                            query = realm.where(Member.class);
                            myDataset = query.findAll();
                            myAdapter = new MyAdapter(myDataset,realm);

                            fileName = DateFormat.format("yy/MM/dd aah:mm", System.currentTimeMillis()).toString();
                            realm.beginTransaction();
                            member = realm.where(Member.class).equalTo("name", userName).findFirst();
                            member.setWriteText(writeText.getText().toString());
                            member.setCurrentTime(fileName);
                           // member.setTime("30");
                            member.setVisable1(false);
                            Integer deleteTime = Integer.parseInt(member.getTime())*10000;
                            Intent intent = new Intent();
                            setResult(Activity.RESULT_OK, intent);
                            startActivity(new Intent(getApplicationContext(),WriteAllActivity.class));
                            finish();
                            realm.commitTransaction();
                            Thread.sleep(deleteTime);
                            //지우는거 넣장--------------------------------------
                            String checkposition;
                            String curemtTime = member.getCurrentTime();
                            Log.i("Position",curemtTime);
                            myAdapter.deleteItem(curemtTime);


                            System.out.println(deleteTime);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }


                        //삭제하는 명령어
                    }
                });

                th.start();
            }
        });

    }
    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    writeText.setText(result.get(0));
                }
                break;
            }
        }
    }

//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        //정리하기
//        realm.removeAllChangeListeners();
//        realm.close();
//    }
}