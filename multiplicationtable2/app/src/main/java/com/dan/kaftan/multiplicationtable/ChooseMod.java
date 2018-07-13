package com.dan.kaftan.multiplicationtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseMod extends AppCompatActivity {
    Button mix;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    int modNum = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mod);

        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        mix = (Button)findViewById(R.id.mix);
        setModNum();
        startGame();





    }


    public void setModNum(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 2;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 3;

            }
        });btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 4;

            }
        });btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 5;

            }
        });btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 6;

            }
        });btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 7;

            }
        });btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 8;

            }
        });btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 9;

            }
        });mix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modNum = 1;

            }
        });


    }
    public int getModNum(){
        return modNum;
    }
    public void startGame(){
        Intent a = new Intent(ChooseMod.this, Game1.class);
startActivity(a);
    }
}
