package com.example.scheduler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MonthActivity extends AppCompatActivity {

    EditText et;
    TextView tvMonth;
    CalendarView Calendar;
    ListView ScheduleList;
    Button addBtn,todoBtn, ddayBtn,backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        tvMonth=findViewById(R.id.tvMonth);
        Calendar = findViewById(R.id.Calendar);
        ScheduleList = findViewById(R.id.ScheduleList);
        addBtn= findViewById(R.id.addBtn);
        todoBtn = findViewById(R.id.todoBtn);
        ddayBtn = findViewById(R.id.ddayBtn);
        et = (EditText) findViewById(R.id.editText);

        final ArrayList<String> dataSet = new ArrayList<>();
        dataSet.add("일정 입력하기");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataSet);
        ScheduleList.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet.add("일정 "+(dataSet.size()+1));
                adapter.notifyDataSetChanged();
            }
        });


        ScheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivityForResult(intent,1);
            }
        });


        ScheduleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MonthActivity.this);
                dlg.setTitle("DAYS");
                dlg.setMessage("일정을 수정, 삭제하시겠습니까?");

                final EditText et = new EditText(getApplicationContext());
                dlg.setView(et);

                dlg.setPositiveButton("Modi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataSet.set(position,et.getText().toString());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataSet.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "삭제", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
                return true;
            }
        });


        todoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        ddayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}