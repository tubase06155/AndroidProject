package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.dal.LessonDAO;

import java.util.ArrayList;
import java.util.List;

public class ManageAddQuestionActivity extends AppCompatActivity {
    EditText addQuestionContent, addQuestionOpt1,addQuestionOpt2,addQuestionOpt3,addQuestionOpt4;
    Spinner spinnerTrueAddQuestion;
    Button addQuestionSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_add_question);
        spinnerTrueAddQuestion = findViewById(R.id.spinnerTrueAddQuestion);
        addQuestionSubmit = findViewById(R.id.addQuestionSubmit);
        addQuestionContent = findViewById(R.id.addQuestionContent);
        addQuestionOpt1 = findViewById(R.id.addQuestionOpt1);
        addQuestionOpt2 = findViewById(R.id.addQuestionOpt2);
        addQuestionOpt3 = findViewById(R.id.addQuestionOpt3);
        addQuestionOpt4 = findViewById(R.id.addQuestionOpt4);

        List<String> listspinnerItem = new ArrayList<String>();
        listspinnerItem.add("1");
        listspinnerItem.add("2");
        listspinnerItem.add("3");
        listspinnerItem.add("4");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listspinnerItem);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrueAddQuestion.setAdapter(dataAdapter);

        addQuestionSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = addQuestionContent.getText().toString();
                String opt1 = addQuestionOpt1.getText().toString();
                String opt2 = addQuestionOpt2.getText().toString();
                String opt3 = addQuestionOpt3.getText().toString();
                String opt4 = addQuestionOpt4.getText().toString();
                int rightOpt = Integer.valueOf(String.valueOf(spinnerTrueAddQuestion.getSelectedItem()));


                Intent intent = getIntent();
                final Lesson lesson =(Lesson)intent.getSerializableExtra("lesson");
                if (content.trim().equals("")|| opt1.trim().equals("")|| opt2.trim().equals("")|| opt3.trim().equals("")|| opt4.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter all the field", Toast.LENGTH_SHORT).show();
                } else {
                    LessonDAO lessonDAO = new LessonDAO();
                    try {
                        lessonDAO.addQuestion(lesson.getLessonID(),content,opt1,opt2,opt3,opt4,rightOpt);
                        Toast.makeText(getApplicationContext(), "Add new Question for lesson Successfully", Toast.LENGTH_SHORT).show();

                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
               


            }
        });

    }
}
