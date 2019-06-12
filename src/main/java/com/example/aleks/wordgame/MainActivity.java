package com.example.aleks.wordgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btn_easy, btn_medium, btn_hard;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_easy = findViewById(R.id.btn_easy);
        btn_medium = findViewById(R.id.btn_medium);
        btn_hard = findViewById(R.id.btn_hard);

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 1;
                Intent intent = new Intent(MainActivity.this, MessageActvity.class);
                intent.putExtra("lvl", level);
                startActivity(intent);
            }
        });


        btn_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 2;
                Intent intent = new Intent(MainActivity.this, MessageActvity.class);
                intent.putExtra("lvl", level);
                startActivity(intent);
            }
        });


        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = 3;
                Intent intent = new Intent(MainActivity.this, MessageActvity.class);
                intent.putExtra("lvl", level);
                startActivity(intent);
            }
        });
    }
}
