package com.dan.kaftan.multiplicationtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game1 extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        setMod();
        getRandomNum();
        chooseFakeAnswers();
        chooseLocationForAnswers();

    }


    int modNum = 0;
    public void setMod(){
        ChooseMod mod = new ChooseMod();
       modNum = mod.getModNum();
    }
    int randNum = 0;
    public void getRandomNum (){
        Random random = new Random(10);
        randNum = random.nextInt() + 1;
    }
    int num1= 0;
    int num2 = 0;
    int trueAnswer = 0;
    public void setQuestion(){
        if (modNum == 1){

        }
        else{
            num1 = modNum;
            num2 = randNum;
            trueAnswer = num1 + num2;

        }

    }
    int fakeAnswer1;
    int fakeAnswer2;
    int fakeAnswer3;
    private void chooseFakeAnswers() {
        Random rand = new Random();
        fakeAnswer1 = rand.nextInt(10) + 1;
        while (fakeAnswer1 == trueAnswer) {
            fakeAnswer1 = rand.nextInt(10) + 1;
        }
        answers.add(fakeAnswer1);


        fakeAnswer2 = rand.nextInt(10) + 1;
        while (fakeAnswer2 == trueAnswer || fakeAnswer2 == fakeAnswer1) {
            fakeAnswer2 = rand.nextInt(10) + 1;
        }
        answers.add(fakeAnswer2);


        fakeAnswer3 = rand.nextInt(10) + 1;
        while (fakeAnswer3 == trueAnswer || fakeAnswer3 == fakeAnswer1 || fakeAnswer3 == fakeAnswer2) {
            fakeAnswer3 = rand.nextInt(10) + 1;
        }
        answers.add(fakeAnswer3);
    }
    List<Integer> answers = new ArrayList<>();
    private void chooseLocationForAnswers() {

        List<Button> btnList = Arrays.asList(btn1, btn2, btn3, btn4);
        Collections.shuffle(btnList);
        for (Button btn : btnList) {
            btn.setText(Integer.toString(answers.remove(0)));
        }
    }
}

