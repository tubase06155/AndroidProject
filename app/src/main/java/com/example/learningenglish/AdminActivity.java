package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    Button manageUser, manageLesson, manageQuestion, manageFeedback ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //1233444
        manageUser = findViewById(R.id.manageUser);
        manageLesson = findViewById(R.id.manageLesson);
        manageQuestion = findViewById(R.id.manageQuestion);
        manageFeedback = findViewById(R.id.manageFeedback);

        manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManageUserActivity.class);
                startActivity(intent);
            }
        });

        manageFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManageFeedback1Activity.class);
                startActivity(intent);
            }
        });
        manageLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManageLessonActivity.class);
                startActivity(intent);
            }
        });
        manageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManageQuestionActivity.class);
                startActivity(intent);
            }
        });


    }
}
