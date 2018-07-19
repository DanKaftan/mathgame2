package com.dan.kaftan.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.concurrent.TimeUnit;

public class EndGame extends AppCompatActivity implements RewardedVideoAdListener {


    TextView tvFinalScore;
    int score = 0;
    int bestScore = 0;
    Button btnStartNewGame;
    String bestScoreString;
    Button btnShare;
    StringBuffer stringBuffer;
    Button btnRevive;
    ImageView ivRevive;
    TextView tvBestScore;
    TextView tvRevive;
    private RewardedVideoAd mRewardedVideoAd;
    private NativeExpressAdView nativeExpressAdView;
    boolean revive = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        getSupportActionBar().hide();
        btnRevive = (Button) findViewById(R.id.btnrevive);
        MobileAds.initialize(this, "ca-app-pub-7775472521601802~5091426220");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        ivRevive = (ImageView) findViewById(R.id.ivrevive);
        tvRevive = (TextView) findViewById(R.id.tvrevive);



        copyReviveFromPrevActivity();
        copyScoreFromPrevActivity();

        if (!revive) {
            loadRewardedVideoAd();
            btnRevive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show();
                        revive = true;
                    }
                }
            });
        } else {

            btnRevive.setVisibility(View.INVISIBLE);
            ivRevive.setVisibility(View.INVISIBLE);
            tvRevive.setVisibility(View.INVISIBLE);

        }


        displayFinalScore();
        share();
        startNewGame();
    }

    // set the text of the final score
    public void displayFinalScore() {

        tvFinalScore = (TextView) findViewById(R.id.tvFinalScore);
        tvFinalScore.setText(Integer.toString(score));
    }


// share app

    public void share() {

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


    public void startNewGame() {
        btnStartNewGame = (Button) findViewById(R.id.btnStartNewGame);
        btnStartNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(EndGame.this, Game.class);
                i.putExtra("revive", revive);
                startActivity(i);
            }

        });


    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }


    @Override
    public void onRewardedVideoAdLoaded() {
        System.out.println("onRewardedVideoAdLoaded");

    }

    @Override
    public void onRewardedVideoAdOpened() {
        System.out.println("onRewardedVideoAdOpened");

    }

    @Override
    public void onRewardedVideoStarted() {
        System.out.println("onRewardedVideoStarted");

    }

    @Override
    public void onRewardedVideoAdClosed() {
        System.out.println("onRewardedVideoAdClosed");

        //TODO need to upgrade listener to a version that supports the completed call back
        onRewardedVideoCompleted();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        System.out.println("onRewarded");


    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        System.out.println("onRewardedVideoAdLeftApplication");

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        System.out.println("onRewardedVideoAdFailedToLoad");

    }


    public void onRewardedVideoCompleted() {
        System.out.println("onRewardedVideoCompleted");

        Intent i = new Intent(EndGame.this, Game.class);
        i.putExtra("revive", revive);
        i.putExtra("score", score);
        startActivity(i);
    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        revive=false;
        super.onDestroy();


    }

    public int getScore() {
        return score;
    }

    public boolean isRevive() {
        return revive;
    }


    public void copyReviveFromPrevActivity() {
        Intent reviveIntent = getIntent(); // gets the previously created intent
        revive = reviveIntent.getBooleanExtra("revive", false);
    }

    public void copyScoreFromPrevActivity() {
        Intent reviveIntent = getIntent(); // gets the previously created intent
        score = reviveIntent.getIntExtra("score", 0);
    }
}