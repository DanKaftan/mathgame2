package com.dan.kaftan.mathgame;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.dan.kaftan.mathgame.targil.BankOfTargils;
import com.dan.kaftan.mathgame.targil.Targil;
import com.dan.kaftan.mathgame.targil.TargilAdd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class Game extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 600000;
    TextView tv;
    int num1;
    int num2;
    List<Integer> answers = new ArrayList<>();
    int fakeAnswer1 = 0;
    int fakeAnswer2 = 0;
    int fakeAnswer3 = 0;
    int trueAnswer = 0;
    TextView tva1;
    TextView tva2;
    TextView tva3;
    TextView tva4;
    TextView timer;
    ImageView iv;
    ImageView hiv1;
    ImageView hiv2;
    ImageView hiv3;
    int invalidationCounter = 0;
    Random rand = new Random();
    int score = 0;
    TextView tvScore;
    boolean finishCheck = false;
    boolean answerCheck = false;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private CountDownTimer mcountDownTimer;
    private CountDownTimer viewResultTimer;
    private boolean mTimerRunning;
    Context context;
    CountDownTimer ab;

    // for disabling sound
    boolean  isVisible = true;

    // sounds
    MediaPlayer correctSound;
    MediaPlayer falseSound;

    boolean gameOver = false;


    // this holds the targilim we want to run
    BankOfTargils bankOfTargils = new BankOfTargils();
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();


        tv = (TextView) findViewById(R.id.tv);

        tva1 = (TextView) findViewById(R.id.tva1);
        tva2 = (TextView) findViewById(R.id.tva2);
        tva3 = (TextView) findViewById(R.id.tva3);
        tva4 = (TextView) findViewById(R.id.tva4);
        iv = (ImageView) findViewById(R.id.iv);
        hiv1 = (ImageView) findViewById(R.id.hiv1);
        hiv2 = (ImageView) findViewById(R.id.hiv2);
        hiv3 = (ImageView) findViewById(R.id.hiv3);
        tvScore = (TextView) findViewById(R.id.score);
        tvScore.setText("score: " + Integer.toString(score));
        timer = (TextView) findViewById(R.id.timer);

        initTargilim();
        correctSound= MediaPlayer.create(Game.this,R.raw.correct);
        falseSound= MediaPlayer.create(Game.this,R.raw.eror);

        setGame();

    }


    public void setGame() {

        if (invalidationCounter == 3) {
            score = 0;
        }
        initGameView();
        //  setTimer();
        // Sound(Context context);
        chooseTargil();




        chooseFakeAnswers();

        chooseLocationForAnswers();

        setTimerForAnswer();

    }


    private void chooseLocationForAnswers() {

        List<TextView> tvaList = Arrays.asList(tva1, tva2, tva3, tva4);
        Collections.shuffle(tvaList);
        for (TextView tva : tvaList) {
            tva.setText(Integer.toString(answers.remove(0)));
        }
    }

    private void chooseFakeAnswers() {
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

    @NonNull
    private void chooseTargil() {

        // take random targil
        Targil targil = bankOfTargils.removeRandomTargil();

        // to display it
        tv.setText(targil.getTargilAsString());

        // calc the targil result
        trueAnswer = targil.calc();

        answers.add(trueAnswer);
    }

    private void initGameView() {

        iv.setImageResource(R.drawable.a);
        tva1.setVisibility(View.VISIBLE);
        tva2.setVisibility(View.VISIBLE);
        tva3.setVisibility(View.VISIBLE);
        tva4.setVisibility(View.VISIBLE);
        tv.setVisibility(View.VISIBLE);
        tvScore.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
    }

    private void initTargilim() {

        // take targilim form bank of targilim
        List<Targil> targilim = bankOfTargils.getTarglilim();

        // fill it with new targilim
        for (int firstNum = 1 ; firstNum < 10; firstNum++){
            for (int secondNum = 1 ; firstNum + secondNum <= 10 ; secondNum++){
                targilim.add(new TargilAdd(firstNum,secondNum,"+"));
            }
        }
    }

    public void tva1OnClick(View v) throws InterruptedException {
        handleClick(v, tva1, false);
    }

    public void tva2OnClick(View v) throws InterruptedException {
        handleClick(v, tva2, false);
    }

    public void tva3OnClick(View v) throws InterruptedException {
        handleClick(v, tva3, false);
    }

    public void tva4OnClick(View v) throws InterruptedException {
        handleClick(v, tva4, false);
    }

    public void handleClick(View v, TextView tva, boolean timeOut) throws InterruptedException {
        try {

            // first, cancel timer as the user clicked
            if (mcountDownTimer != null) {
                mcountDownTimer.cancel();
            }

            int tvaNum = 0;
            if (tva != null) {
                tvaNum = Integer.parseInt(tva.getText().toString());
            }
            tva1.setVisibility(View.INVISIBLE);
            tva2.setVisibility(View.INVISIBLE);
            tva3.setVisibility(View.INVISIBLE);
            tva4.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
            tvScore.setVisibility(View.INVISIBLE);
            timer.setVisibility(View.INVISIBLE);

            if (!timeOut && tvaNum == trueAnswer) {
                iv.setImageResource(R.drawable.vi);

                // do not disturb with sounds if not visible
                if(isVisible){
                    correctSound.start();
                }
                //   correctSoundEffect(context);
                score = score + 10;
                answerCheck = true;


                //result();
            } else {
                iv.setImageResource(R.drawable.x);

                // do not disturb with sounds if not visible
                if(isVisible){
                    falseSound.start();
                }
                answerCheck = false;
                invalidationCounter = invalidationCounter + 1;
                //     result();

                if (invalidationCounter == 1) {
                    hiv3.setVisibility(View.INVISIBLE);
                }
                if (invalidationCounter == 2) {
                    hiv2.setVisibility(View.INVISIBLE);
                }
                if (invalidationCounter == 3) {
                    hiv1.setVisibility(View.INVISIBLE);
                }
            }
            tvScore.setText("score: " + Integer.toString(score));

            if (invalidationCounter != 3) {
                setTimerForViewResult();
            } else {
                setTimerForGameOver();
            }

        } catch (Exception e) {

        }

//            new CountDownTimer(3000, 2000) { //40000 milli seconds is total time, 1000 milli seconds is time interval
//                public void onTick(long millisUntilFinished) {
//                    try {
//                        TimeUnit.SECONDS.sleep(2);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if (invalidationCounter != 3) {
//                        setGame();
//
//                    } else {
//                        iv.setImageResource(R.drawable.gameover);
//                        tvFinalScore.setVisibility(View.VISIBLE);
//                        tvFinalScore.setText("FINAL SCORE: " + Integer.toString(score));
//                        tvScore.setText("score: 0");
//
//                        btn1.setVisibility(View.VISIBLE);
//                        score = 0;
//                        btn1.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                try {
//                                    TimeUnit.SECONDS.sleep(1);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                setGame();
//                                hiv1.setVisibility(View.VISIBLE);
//                                hiv2.setVisibility(View.VISIBLE);
//                                hiv3.setVisibility(View.VISIBLE);
//                                invalidationCounter = 0;
//                                score = 0;
//                            }
//                        });
//
//                    }
//                }
//
//                public void onFinish() {
//
//                }
//
//            }.start();
    }

    public void gameOver() {

        if (isVisible){
            Intent a = new Intent(Game.this, EndGame.class);
            a.putExtra("score", score);
            startActivity(a);
            /*setGame();
            hiv1.setVisibility(View.VISIBLE);
            hiv2.setVisibility(View.VISIBLE);
            hiv3.setVisibility(View.VISIBLE);
            invalidationCounter = 0;
            score = 0;*/
        } else {
            gameOver = true;
        }
    }

    public void setTimerForAnswer() {
        mcountDownTimer = new CountDownTimer(11000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval
            int timerNum = 10;

            public void onTick(long millisUntilFinished) {
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                timer.setText(Integer.toString(timerNum));
                timerNum = timerNum - 1;

            }

            public void onFinish() {
                try {
                    handleClick(null, null, true);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }.start();
    }

    public void setTimerForViewResult() {
        viewResultTimer = new CountDownTimer(2000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                setGame();
            }
        }.start();
    }

    public void setTimerForGameOver() {
        viewResultTimer = new CountDownTimer(2000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                gameOver();

            }
        }.start();
    }

    public void share() {


    }



  /* public void Sound(Context context) {
        //soundPool (int MaxStreams, int streamType, int srcQuality)
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        hitSound = soundPool.load(context, R.raw.eror, 1);
        soundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }*/

    /* public void setTimer() {
         new CountDownTimer(3000, 1000) {

             public void onTick(long millisUntilFinished) {
                 timer.setText("" + millisUntilFinished / 1000);
                 //here you can have your logic to set text to edittext
             }

             public void onFinish() {
                 timer.setText("done!");

                 }
            }.start();

 */



    private SoundPool sounds;
    private int sExplosion;

public void correctSoundEffect (){


    //declare variables
    sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
    sExplosion = sounds.load(context, R.raw.correct, 1);
    sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);


}
public void falseSoundEffect (){

}

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        isVisible = hasFocus;
        if (isVisible && gameOver) {
            gameOver = false;
            gameOver();
        }
    }

}










