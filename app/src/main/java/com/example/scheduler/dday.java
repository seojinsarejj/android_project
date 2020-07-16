package com.example.scheduler;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class dday extends Fragment {

    Button plus;
    ListView listView;


    ArrayList<ItemVO> dataList = new ArrayList<ItemVO>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View viewDday;
        viewDday= inflater.inflate(R.layout.fragment_dday, container, false);


        plus = viewDday.findViewById(R.id.plus);
        listView = viewDday.findViewById(R.id.listview);

        listSetUp(dataList);

        // 리스트뷰에 어댑터 연결


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),SecondActivity.class);
                startActivityForResult(intent,0);
            }
        });


        return viewDday;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        String title = data.getStringExtra("title");
        String date = data.getStringExtra("date");
        String dayCount = data.getStringExtra("dayCount");

        if(dayCount=="0"){
            dataList.add(new ItemVO(title,date,"D-day"));
        }else{
            dataList.add(new ItemVO(title,date,"D-" + dayCount));
        }

        listSetUp(dataList);

    }

    void listSetUp(ArrayList<ItemVO> arrayList){
        CustomAdapter adapter = new CustomAdapter(getActivity().getApplicationContext(), R.layout.row,arrayList);
        listView.setAdapter(adapter);
    }


}
