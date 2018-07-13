package com.dan.kaftan.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class EndGame extends AppCompatActivity {


    TextView tvFinalScore;
    int finalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        setFinalScore();
        share();
        startNewGame();
    }

    // set the text of the final score
    public void setFinalScore(){

        Intent myIntent = getIntent(); // gets the previously created intent
        finalScore = myIntent.getIntExtra("score",0);

        tvFinalScore = (TextView)findViewById(R.id.tvFinalScore);
        tvFinalScore.setText( "FINAL SCORE: " + Integer.toString(finalScore));
    }



// share app

    Button btnShare;
    public void share (){

        btnShare = (Button) findViewById(R.id.btnshare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Amazing math game for first graders: https://play.google.com/store/apps/details?id=com.dan.kaftan.mathgame";
                String shareSub = "You might like this app:";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "share using"));

            }
        });


    }



    //start new game
    // test
    //test2
    //test3

    Button btnStartNewGame;
    public void startNewGame (){
        btnStartNewGame =(Button)findViewById(R.id.btnStartNewGame);
        btnStartNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(EndGame.this, Game.class);
                startActivity(i);
            }

        });



    }
}
