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
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.dal.LessonDAO;

import java.util.ArrayList;
import java.util.List;

public class ManageLessonEditingActivity extends AppCompatActivity {
    EditText editLessonTitle, editLessonContent;
    RadioButton rdEditLessonReading, rdEditLessonGrammar;
    Spinner spinnerEditLessonDifficulty;
    Button editLessonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_lesson_editing);
        editLessonTitle = findViewById(R.id.editLessonTitle);
        editLessonContent = findViewById(R.id.editLessonContent);
        rdEditLessonReading = findViewById(R.id.rdEditLessonReading);
        rdEditLessonGrammar = findViewById(R.id.rdEditLessonGrammar);
        spinnerEditLessonDifficulty = findViewById(R.id.spinnerEditLessonDifficulty);
        editLessonSubmit = findViewById(R.id.editLessonSubmit);

        List<String> listspinnerItem = new ArrayList<String>();
        listspinnerItem.add("1");
        listspinnerItem.add("2");
        listspinnerItem.add("3");
        listspinnerItem.add("4");
        listspinnerItem.add("5");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listspinnerItem);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditLessonDifficulty.setAdapter(dataAdapter);

        Intent intent = getIntent();
        final Lesson lesson =(Lesson)intent.getSerializableExtra("lesson");
        editLessonTitle.setText(lesson.getTitle());
        editLessonContent.setText(lesson.getContent());
        if (lesson.getCategory() == 3 ) rdEditLessonGrammar.setChecked(true);
        else if (lesson.getCategory() == 1) rdEditLessonReading.setChecked(true);
        //else rdEditLessonGrammar.setChecked(true);
        if (lesson.getDifficulty() == 1)
            spinnerEditLessonDifficulty.setSelection(0);
        else if (lesson.getDifficulty() == 2)
            spinnerEditLessonDifficulty.setSelection(1);
        else if (lesson.getDifficulty() == 3)
            spinnerEditLessonDifficulty.setSelection(2);
        else if (lesson.getDifficulty() == 4)
            spinnerEditLessonDifficulty.setSelection(3);
        else if (lesson.getDifficulty() == 5)
            spinnerEditLessonDifficulty.setSelection(4);
        editLessonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editLessonTitle.getText().toString();
                String content = editLessonContent.getText().toString();
                int skill = (rdEditLessonReading.isChecked()) ? 1 : 3;
                int difficulty = Integer.valueOf(String.valueOf(spinnerEditLessonDifficulty.getSelectedItem()));
                if (title.trim().equals("")|| content.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter all the field", Toast.LENGTH_SHORT).show();
                } else {
                    LessonDAO lessonDAO = new LessonDAO();
                    try {
                        lessonDAO.updateLesson(title,skill,content,difficulty,lesson.getLessonID());
                        Toast.makeText(getApplicationContext(), "Edit this Lesson Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult(203,intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}
