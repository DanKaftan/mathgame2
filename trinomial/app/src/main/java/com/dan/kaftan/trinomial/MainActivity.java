package com.dan.kaftan.trinomial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText co1;
    EditText co2;
    EditText co3;
    TextView ans1;
    TextView ans2;
    TextView tv;
    double a;
    double b;
    double c;
    double fixedA;
    double fixedB;
    double fixedC;
    String etNum1;
    String etNum2;
    String etNum3;
    double Answer1;
    double Answer2;
    Button btn;
    int i;
    double roundAnswer1;
    double roundAnswer2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        co1 = (EditText) findViewById(R.id.co1);
        co2 = (EditText) findViewById(R.id.co2);
        co3 = (EditText) findViewById(R.id.co3);
        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        btn = (Button) findViewById(R.id.btn);



        }
        public void onClick(View v){
            setNum();
            getAnswer();

        }


       void setNum() {
    etNum1 = co1.getText().toString();
        if (!etNum1.equals("")) {
            a = Integer.parseInt(etNum1);
        }

          etNum2 = co2.getText().toString();
          if (!etNum2.equals("")) {
          b = Integer.parseInt(etNum2);
        }
         etNum3 = co3.getText().toString();
        if (!etNum3.equals("")) {
           c = Integer.parseInt(etNum3);
          }

    }



    void getAnswer() {
               QuadraticEquation aa = new QuadraticEquation(a, b, c);
               Answer1 = aa.getAnswer1();
               Answer2 = aa.getAnswer2();
               roundAnswer1 = Math.round(Answer1 * 1000);
               roundAnswer2 = Math.round(Answer2 * 1000);


               ans1.setText(Double.toString(Math.round(roundAnswer1)));
        ans2.setText(Double.toString(Math.round(roundAnswer2)));





           }



    }



