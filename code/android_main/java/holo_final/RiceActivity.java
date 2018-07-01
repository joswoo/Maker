package com.example.qhdud.holo_final;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class RiceActivity extends AppCompatActivity {

    EditText et;
    ListView listview;
    RiceViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice);



        adapter = new RiceViewAdapter();
        listview = (ListView)findViewById(R.id.listview1);
        listview.setAdapter(adapter);
        et = (EditText)findViewById(R.id.SearchText);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            @Override
            public void afterTextChanged(Editable edit) {
                String s = edit.toString();
                Integer flag = 0;
                if (s.equals("김치") || s.equals("볶음밥")) {
                    // 첫 번째 아이템 추가.
                    adapter.addItem(ContextCompat.getDrawable(RiceActivity.this, R.drawable.rice3));
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                RiceViewItem item = (RiceViewItem) parent.getItemAtPosition(position) ;
                setContentView(R.layout.rice2);
            }
        }) ;


    }

}
