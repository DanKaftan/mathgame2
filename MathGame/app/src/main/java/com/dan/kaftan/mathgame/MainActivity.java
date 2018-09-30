package com.dan.kaftan.mathgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button settingsBtn;
    int i = 0;
    private static final String TAG = "MainActivity";
    private AdView mAdView;
    MediaPlayer mainActivityBackgroud;
    boolean isVisible =true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        settingsBtn = (Button)findViewById(R.id.settings_btn);




        mainActivityBackgroud= MediaPlayer.create(MainActivity.this,R.raw.a);
        mainActivityBackgroud.setLooping(true);
        mainActivityBackgroud.start();


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }

    public void startGameClick(View view){
        mainActivityBackgroud.pause();
        Intent i = new Intent(MainActivity.this, Game.class);
        startActivity(i);

    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        isVisible = hasFocus;
        if (!isVisible){
            mainActivityBackgroud.pause();
        }
        else {
            mainActivityBackgroud.start();
        }


    }
    public void settingsClick(View view){
        mainActivityBackgroud.pause();
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);

    }


}
