package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button dday,todolist,month;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    LinearLayout fragmentView;

    dday d;
    todo t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dday = findViewById(R.id.dday);
        todolist= findViewById(R.id.todolist);
        month = findViewById(R.id.month);
        fragmentView = findViewById(R.id.fragmentview);
        fragmentManager = getSupportFragmentManager();

        d = new dday();
        t = new todo();

        dday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentview,d);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentview,t);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MonthActivity.class);
                startActivityForResult(intent,1);
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

    }


}
