package com.example.qhdud.holo_final;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class FirstStartActivity extends AppCompatActivity {

    private ViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
    }

    private View.OnClickListener mCloseButtonClick = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            int infoFirst = 1;
            SharedPreferences a = getSharedPreferences("a",MODE_PRIVATE);
            SharedPreferences.Editor editor = a.edit();
            editor.putInt("First",infoFirst);
            editor.commit();
            (Toast.makeText(getApplicationContext(),"HOLO에 오신 것을 환영합니다 :-)",Toast.LENGTH_LONG)).show();
            finish();
        }
    };

    /*PagerAdapter*/
    private class PagerAdapterClass extends PagerAdapter {
        private LayoutInflater mInflater;

        public PagerAdapterClass(Context c) {
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object instantiateItem(View pager, int position) {
            View v = null;
            if (position == 0) {
                v = mInflater.inflate(R.layout.firststartview1, null);
                v.findViewById(R.id.fsv_one);
            }
            else if(position == 1)
            {
                v = mInflater.inflate(R.layout.firststartview2,null);
                v.findViewById(R.id.fsv_two);
            }
            else if(position == 2)
            {
                v = mInflater.inflate(R.layout.firststartview3,null);
                v.findViewById(R.id.fsv_three);
            }
            else
            {
                v = mInflater.inflate(R.layout.firststartview4,null);
                v.findViewById(R.id.fsv_four);
                v.findViewById(R.id.close).setOnClickListener(mCloseButtonClick);
            }
            ((ViewPager)pager).addView(v,0);
            return v;
        }

        @Override
        public void destroyItem(View pager, int position, Object view)
        {
            ((ViewPager)pager).removeView((View)view);
        }
        @Override
        public boolean isViewFromObject(View pager,Object obj)
        {
            return pager == obj;
        }
        @Override public void restoreState(Parcelable arg0, ClassLoader arg1){};
        @Override public Parcelable saveState() { return null; };
        @Override public void startUpdate(View arg0) {};
        @Override public void finishUpdate(View arg0) {};
    }//나중에 확인해보자
}
