package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.learningenglish.Entity.User;

public class UserActivity extends AppCompatActivity {
    TextView tvUsername, tvScore;
    Button userReading, userGrammar, userFeedback, userCheckRank;

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

        Intent intent = getIntent();
        final User user =(User)intent.getSerializableExtra("user");

        tvUsername.setText("Welcome " + user.getUsername() + "  !" );
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

    }
}
