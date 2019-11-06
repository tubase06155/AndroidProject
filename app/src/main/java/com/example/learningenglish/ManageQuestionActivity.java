package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.Question;
import com.example.learningenglish.adapter.ManageLessonAdapter;
import com.example.learningenglish.adapter.QuestionAdapter;
import com.example.learningenglish.dal.LessonDAO;
import com.example.learningenglish.dal.QuestionDAO;

import java.util.List;

public class ManageQuestionActivity extends AppCompatActivity {
    ListView listViewManageQuestion;
    QuestionAdapter questionAdapter;
    List<Question> listQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_question);
        listViewManageQuestion = findViewById(R.id.listViewManageQuestion);

    }
    public void setLessonAdapter(){

        try {
            QuestionDAO questionDAO = new QuestionDAO();
            listQuestion = questionDAO.listQuestion();
            questionAdapter = new QuestionAdapter(this, R.layout.manage_question_layout, listQuestion);
            listViewManageQuestion.setAdapter(questionAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
