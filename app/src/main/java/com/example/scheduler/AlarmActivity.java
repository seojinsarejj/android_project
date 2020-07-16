package com.example.scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Month;


public class AlarmActivity extends AppCompatActivity {

    TimePicker timePicker;
    Button alarmBtn,onBtn, offBtn, mapBtn;
    int hour, minute;
    EditText titleET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        titleET = findViewById(R.id.titleET);
        alarmBtn = (Button)findViewById(R.id.alarmBtn);
        onBtn = (Button)findViewById(R.id.onBtn);
        offBtn = (Button)findViewById(R.id.offBtn);
        mapBtn = (Button)findViewById(R.id.mapBtn);
        timePicker = findViewById(R.id.timePicker);

       /* TimePicker mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = mTimePicker.getHour() + "";
            minute = mTimePicker.getMinute() + "";
        } else {
            hour = mTimePicker.getCurrentHour() + "";
            minute = mTimePicker.getCurrentMinute() + "";
        }*/
       onBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent inIntent = new Intent(getApplicationContext(),MonthActivity.class);
               inIntent.putExtra("title",titleET.getText().toString());
               startActivity(inIntent);
           }
       });

       offBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       mapBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), MapActivity.class);
               startActivity(intent);
           }
       });
    }
}
