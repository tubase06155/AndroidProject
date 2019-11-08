package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class WelcomeActiviy extends AppCompatActivity {

    TextView tv ;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_activiy);
        tv = findViewById(R.id.textView);
//        Animation myanims = AnimationUtils.loadAnimation(this,R.anim.)
//        tv.setAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(WelcomeActiviy.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
