package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learningenglish.Entity.User;

public class UserActivity extends AppCompatActivity {
    TextView tvUsername, tvScore;
    Button userReading, userGrammar, userFeedback, userCheckRank;
    ImageView background_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tvUsername = findViewById(R.id.tvUsername);
        tvScore = findViewById(R.id.tvScore);
        userReading = findViewById(R.id.userReading);
        userGrammar = findViewById(R.id.userGrammar);
        userFeedback = findViewById(R.id.userFeedback);
        userCheckRank = findViewById(R.id.userCheckRank);
        background_top = findViewById(R.id.background_top);

        AnimationDrawable animationDrawable = (AnimationDrawable)background_top.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        Intent intent = getIntent();
        final User user =(User)intent.getSerializableExtra("user");

        String name = user.getUsername();
        String s1 = name.substring(0, 1).toUpperCase();;

        tvUsername.setText( s1 + name.substring(1));
        tvScore.setText("Your score: " +user.getScore() );

        userCheckRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckRankActivity.class);
                startActivity(intent);
            }
        });
        userFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        userGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GrammarActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        userReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReadingActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

    }
}
