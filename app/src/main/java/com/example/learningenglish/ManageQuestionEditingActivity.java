package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.Question;
import com.example.learningenglish.dal.LessonDAO;
import com.example.learningenglish.dal.QuestionDAO;

import java.util.ArrayList;
import java.util.List;

public class ManageQuestionEditingActivity extends AppCompatActivity {
    EditText editQuestionContent, editQuestionOpt1,editQuestionOpt2,editQuestionOpt3,editQuestionOpt4;
    Spinner spinnerTrueEditQuestion;
    Button editQuestionSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_question_editing);
        spinnerTrueEditQuestion = findViewById(R.id.spinnerTrueEditQuestion);
        editQuestionSubmit = findViewById(R.id.editQuestionSubmit);
        editQuestionContent = findViewById(R.id.editQuestionContent);
        editQuestionOpt1 = findViewById(R.id.editQuestionOpt1);
        editQuestionOpt2 = findViewById(R.id.editQuestionOpt2);
        editQuestionOpt3 = findViewById(R.id.editQuestionOpt3);
        editQuestionOpt4 = findViewById(R.id.editQuestionOpt4);

        List<String> listspinnerItem = new ArrayList<String>();
        listspinnerItem.add("1");
        listspinnerItem.add("2");
        listspinnerItem.add("3");
        listspinnerItem.add("4");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listspinnerItem);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrueEditQuestion.setAdapter(dataAdapter);


        Intent intent = getIntent();
        final Question question =(Question)intent.getSerializableExtra("question");
        editQuestionContent.setText(question.getContent());
        editQuestionOpt1.setText(question.getOpt1());
        editQuestionOpt2.setText(question.getOpt2());
        editQuestionOpt3.setText(question.getOpt3());
        editQuestionOpt4.setText(question.getOpt4());

        if (question.getRightOpt() == 1)
            spinnerTrueEditQuestion.setSelection(0);
        else   if (question.getRightOpt() == 2)
            spinnerTrueEditQuestion.setSelection(1);
        else  if (question.getRightOpt() == 3)
            spinnerTrueEditQuestion.setSelection(2);
        else  if (question.getRightOpt() == 4)
            spinnerTrueEditQuestion.setSelection(3);
        editQuestionSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = editQuestionContent.getText().toString();
                String opt1 = editQuestionOpt1.getText().toString();
                String opt2 = editQuestionOpt2.getText().toString();
                String opt3 = editQuestionOpt3.getText().toString();
                String opt4 = editQuestionOpt4.getText().toString();
                int rightOpt = Integer.valueOf(String.valueOf(spinnerTrueEditQuestion.getSelectedItem()));

                if (opt1.trim().equals("")||opt2.trim().equals("")||opt3.trim().equals("")||opt4.trim().equals("")|| content.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter all the field", Toast.LENGTH_SHORT).show();
                } else {
                    QuestionDAO questionDAO = new QuestionDAO();
                    try {
                        questionDAO.updateQuestion(question.getQuestionID() +"",content,opt1,opt2,opt3,opt4,rightOpt + "");
                        Toast.makeText(getApplicationContext(), "Edit this Question Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult(204,intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
