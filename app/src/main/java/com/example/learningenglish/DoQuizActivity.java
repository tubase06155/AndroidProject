package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.Question;
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.adapter.LessonAdapter;
import com.example.learningenglish.adapter.QuizAdapter;
import com.example.learningenglish.dal.QuestionDAO;

import java.util.List;

public class DoQuizActivity extends AppCompatActivity {
    ListView listViewDoQuiz;
    Button doQuizSubmit;
    QuizAdapter quizAdapter;
    List<Question> listQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_quiz);
        listViewDoQuiz = findViewById(R.id.listViewDoQuiz);
        doQuizSubmit = findViewById(R.id.doQuizSubmit);

        Intent intent = getIntent();
        Lesson lesson = (Lesson) intent.getSerializableExtra("lesson");
        User user = (User) intent.getSerializableExtra("user");

        try {
            listQuestion = new QuestionDAO().listQuestionByLessonID(lesson.getLessonID() + "");
            quizAdapter = new QuizAdapter(this, R.layout.quiz_layout, listQuestion);
            listViewDoQuiz.setAdapter(quizAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerForContextMenu(listViewDoQuiz);


    }
}
