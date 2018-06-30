package com.example.qhdud.holo_final;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private RealmResults<Member> mDataset; //MainActivity에 item class를 정의해 놓았음
    Realm realm;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 사용될 항목들 선언
        public TextView mText;//내용
        public TextView mName;//닉네임
        public TextView mTime;//시간
        public TextView mTimeSet;//timer

        public ViewHolder(View v) {
            super(v);
            mTime = (TextView)v.findViewById(R.id.info_time);
            mText= (TextView) v.findViewById(R.id.info_text);
            mName = (TextView) v.findViewById(R.id.info_name);
            mTimeSet =(TextView) v.findViewById(R.id.info_timeSet);
        }
    }

    // 생성자 - 넘어 오는 데이터파입에 유의
    public MyAdapter(RealmResults<Member> myDataset) {
        mDataset = myDataset;
    }

    public MyAdapter(RealmResults<Member> myDataset,Realm realm) {
        mDataset = myDataset;
        this.realm = realm;
    }

    //뷰홀더
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

        ViewHolder holder = new ViewHolder(v);
        Log.i("oncreate","나타나라");
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        //Member rowData = mDataset.get(position);
        Log.i("호이", mDataset.get(position).getTime());
        if (mDataset.get(position).getVisable1()) {

        } else {
            Log.i("호이566", "EKEKEK");
            holder.mName.setText(mDataset.get(position).getName()+"님");
            holder.mText.setText(mDataset.get(position).getWriteText());
            holder.mTime.setText(mDataset.get(position).getCurrentTime());
            holder.mTimeSet.setText(mDataset.get(position).getTime() + "분 뒤 사라짐");
        }

    }
    void deleteItem(final int index1) {
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                //아이디 가져오는걸 여기다가 넣고
                //mDataset.indexOf()
              //  if(mDataset != null) {
                    mDataset.get(index1).deleteFromRealm();
                    notifyItemRemoved(index1);
                //}else
                //{
                 //   return;
                //}

            }
        });

        //mDataset.remove(index1);
        //notifyItemRemoved(index1);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}