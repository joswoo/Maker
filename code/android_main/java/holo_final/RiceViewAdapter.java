package com.example.qhdud.holo_final;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class RiceViewAdapter extends BaseAdapter {

    private ArrayList<RiceViewItem> listViewItemList = new ArrayList<RiceViewItem>() ;

    public RiceViewAdapter()
    {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.rice, viewGroup, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) view.findViewById(R.id.imageView2) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        RiceViewItem listViewItem = listViewItemList.get(i);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());

        return view;

    }
    public void addItem(Drawable icon) {
        RiceViewItem item = new RiceViewItem();

        item.setIcon(icon);


        listViewItemList.add(item);
    }



}
