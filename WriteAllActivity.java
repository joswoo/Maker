package com.example.qhdud.holo_final;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class WriteAllActivity extends AppCompatActivity {

    Realm realm;
    String userName;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RealmQuery<Member> query;
    private RealmResults<Member> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_all);

        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        userName = preferences.getString("name", "");
        //Realm 인스턴스를 얻는다.
        realm.init(getApplicationContext());//추가
        realm = Realm.getDefaultInstance();
        //쿼리
        query = realm.where(Member.class);
        results = query.findAll();
        results = results.sort("currentTime", Sort.DESCENDING); //내림차순으로 변경

        //리스너추가 - 지켜 보고 있다.
        results.addChangeListener(new RealmChangeListener<RealmResults<Member>>() {
            @Override
            public void onChange(RealmResults<Member> element) {

                mAdapter = new MyAdapter(results);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        //어답터 설정
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);//옵션
        //Linear layout manager 사용
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //어답터 세팅
        mAdapter = new MyAdapter(results);
        mRecyclerView.setAdapter(mAdapter);

    }

    //모든 realm 인스턴스들을 닫아 준다. 메모리관리 중요
    @Override
    public void onDestroy(){
        super.onDestroy();
        //정리하기
        realm.removeAllChangeListeners();
        realm.close();
    }
}