package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.learningenglish.Entity.Lesson;

public class LearnLessonActivity extends AppCompatActivity {
    TextView learnLessonTitle, learnLessonContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_lesson);
        learnLessonContent = findViewById(R.id.learnLessonContent);
        learnLessonTitle = findViewById(R.id.learnLessonTitle);
        Intent intent = getIntent();
        Lesson lesson = (Lesson) intent.getSerializableExtra("lesson");
        learnLessonTitle.setText(lesson.getTitle());
        learnLessonContent.setText(lesson.getContent());

    }
}
