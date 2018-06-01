package com.example.deepak.fitalways;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button startWorkout,showData;
    private DatabaseHelper Mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startWorkout = findViewById(R.id.start_workout);
        showData = findViewById(R.id.show_data);

        startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StartWorkoutActivity.class);
                startActivity(intent);
            }
        });

        Mydb = new DatabaseHelper(this);

        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = Mydb.getAllData();
                if (res.getCount() == 0){
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name : "+res.getString(2));
                    buffer.append("Date : "+res.getString(1));
                    buffer.append("Exercises : "+res.getString(6));
                }
                showData(buffer.toString());
            }
        });
    }
    private void showData(String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Data");
        builder.setMessage(Message);
        builder.show();
    }
}
