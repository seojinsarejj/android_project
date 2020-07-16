package com.example.scheduler;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class todo extends Fragment {


    Button plus;
    ListView listView;

    Calendar cal = Calendar.getInstance();
    int nowYear = cal.get ( cal.YEAR );
    int nowMonth = cal.get ( cal.MONTH ) + 1 ;
    int nowDate = cal.get ( cal.DATE ) ;

    int y=0, m=0, d=0;

    ArrayList<String> dataList = new ArrayList<String>();


    HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View todoView ;
        todoView = inflater.inflate(R.layout.fragment_todo, container, false);

        plus = todoView.findViewById(R.id.plus);
        listView = todoView.findViewById(R.id.listview);

        listSetUp(dataList);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent outIntent = new Intent(getActivity().getApplicationContext(),todo_second.class);
                outIntent.putExtra("day",dataList.get(position));
                outIntent.putExtra("schedule",hashMap.get(dataList.get(position)));
                startActivityForResult(outIntent,0);
            }
        });



        return todoView;
    }




    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month+1;
                d = dayOfMonth;
                dataList.add(m+"/"+d);
                listSetUp(dataList);

                ArrayList<String> data = new ArrayList<>();
                hashMap.put(m+"/"+d,data);


            }
        },nowYear, nowMonth-1, nowDate);

        datePickerDialog.show();


    }





    void listSetUp(ArrayList<String> arrayList){
        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,android.R.id.text1,dataList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(data ==null) {
            Toast.makeText(getActivity(),"null",Toast.LENGTH_SHORT).show();
        }else{
            String date = data.getStringExtra("date");
            ArrayList<String> schedule = data.getStringArrayListExtra("schedule");
            for(int i = 0; i<schedule.size();i++) {
                System.out.println(schedule.get(i));
            }
            hashMap.remove(date);
            hashMap.put(date,schedule);
        }


    }


}
