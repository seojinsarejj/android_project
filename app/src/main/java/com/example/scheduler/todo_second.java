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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class todo_second extends AppCompatActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    TextView todolisttitle;
    Button plus,minus,returnBtn;
    ListView listView;

    int index = -1;

    ArrayList<ItemVO2> dataList = new ArrayList<ItemVO2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_second);


        todolisttitle = findViewById(R.id.todolisttitle);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        listView = findViewById(R.id.listview);
        returnBtn = findViewById(R.id.btnReturn);

        final Intent inIntent = getIntent();
        final String title = inIntent.getStringExtra("day");
        todolisttitle.setText(title);
        final ArrayList<String> schedule = inIntent.getStringArrayListExtra("schedule");


        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice, schedule);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setAdapter(adapter);


        for(int i = 0; i<schedule.size();i++){
            dataList.add(new ItemVO2(schedule.get(i)));
            adapter.notifyDataSetChanged();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(todo_second.this);
                dlg.setTitle(todolisttitle.getText());
                dlg.setMessage("일정 추가");
                dlg.setIcon(R.mipmap.ic_launcher_round);
                final EditText et = new EditText(getApplicationContext());
                dlg.setView(et);
                dlg.setNegativeButton("취소",null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        schedule.add(et.getText().toString());
                        dataList.add(new ItemVO2(et.getText().toString()));
                        adapter.notifyDataSetChanged();
                    }
                });
                dlg.show();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedule.remove(index);
                adapter.notifyDataSetChanged();
                listView.clearChoices();

            }
        });



        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent outIntent = new Intent(todo_second.this,MainActivity.class);

                outIntent.putExtra("date" ,title);
                outIntent.putExtra("schedule",schedule);
                setResult(0,outIntent);
                finish();
            }
        });




    }


    void listSetUp(ArrayList<ItemVO> arrayList){
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.row,arrayList);
        listView.setAdapter(adapter);
    }

}
