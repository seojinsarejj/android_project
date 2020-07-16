package com.example.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter2 extends BaseAdapter {

    Context context;
    int resource;
    ArrayList<ItemVO2> dataList;

    public CustomAdapter2(Context context, int resource, ArrayList<ItemVO2> dataList) {
        this.context = context;
        this.resource = resource;
        this.dataList = dataList;
    }


    // 리스트뷰의 아이템 개수 반환
    @Override
    public int getCount() {
        return dataList.size();
    }

    // 리스트뷰의 아이템 반환
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.todo_row, null); // R.layout.row xml을 view 객체로 생성
        }

        TextView title = convertView.findViewById(R.id.textView2);
        final CheckBox checkBox = convertView.findViewById(R.id.checkbox);



        title.setText(dataList.get(position).getContentsStr());

        return convertView;
    }
}
