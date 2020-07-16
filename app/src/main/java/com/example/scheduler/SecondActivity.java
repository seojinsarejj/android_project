package com.example.scheduler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class SecondActivity extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    int nowYear = cal.get ( cal.YEAR );
    int nowMonth = cal.get ( cal.MONTH ) + 1 ;
    int nowDate = cal.get ( cal.DATE ) ;

    int y=0, m=0, d=0;

    EditText editTitle;
    Button btnDate,btnReturn;
    TextView date;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTitle = findViewById(R.id.editTitle);
        btnDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.date);
        btnReturn = findViewById(R.id.btnReturn);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);

                int dayCount = dayCount(y,m,d);

                outIntent.putExtra("date" , date.getText().toString());
                outIntent.putExtra("title",editTitle.getText().toString());
                outIntent.putExtra("dayCount",Integer.toString(dayCount));
                setResult(0,outIntent);
                finish();

            }
        });



    }


    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month+1;
                d = dayOfMonth;
                date.setText(y+ "." + m + "."+ d);
            }
        },nowYear, nowMonth-1, nowDate);

        datePickerDialog.show();
    }


    int dayCount(int y, int m,int d) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar todaCal = Calendar.getInstance(); //오늘날자 가져오기
        Calendar ddayCal = Calendar.getInstance(); //오늘날자를 가져와 변경시킴


        m -= 1;
        ddayCal.set(y,m,d);// D-day의 날짜를 입력

        long today = todaCal.getTimeInMillis()/86400000; //->(24 * 60 * 60 * 1000) 24시간 60분 60초 * (ms초->초 변환 1000)
        long dday = ddayCal.getTimeInMillis()/86400000;
        long count = dday - today; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.

        return (int)count;
    }

}
