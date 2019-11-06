package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningenglish.dal.LessonDAO;

import java.util.ArrayList;
import java.util.List;

public class ManageAddLessonActivity extends AppCompatActivity {
    TextView addLessonTitle, addLessonContent;
    RadioButton rdAddLessonReading, rdAddLessonGrammar;
    Spinner spinnerAddLessonDifficulty;
    Button addLessonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_add_lesson);
        addLessonContent = findViewById(R.id.addLessonContent);
        addLessonTitle = findViewById(R.id.addLessonTitle);
        rdAddLessonReading = findViewById(R.id.rdAddLessonReading);
        rdAddLessonGrammar = findViewById(R.id.rdAddLessonGrammar);
        spinnerAddLessonDifficulty = findViewById(R.id.spinnerAddLessonDifficulty);
        addLessonSubmit = findViewById(R.id.addLessonSubmit);
        List<String> listspinnerItem = new ArrayList<String>();
        listspinnerItem.add("1");
        listspinnerItem.add("2");
        listspinnerItem.add("3");
        listspinnerItem.add("4");
        listspinnerItem.add("5");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listspinnerItem);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddLessonDifficulty.setAdapter(dataAdapter);

        addLessonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = addLessonTitle.getText().toString();
                String content = addLessonContent.getText().toString();
                int skill = (rdAddLessonReading.isChecked()) ? 1 : 3;
                int difficulty = Integer.valueOf(String.valueOf(spinnerAddLessonDifficulty.getSelectedItem()));
                if (title.trim().equals("")|| content.trim().equals("")) {
                    Toast.makeText(ManageAddLessonActivity.this, "Enter all the field", Toast.LENGTH_SHORT).show();
                } else {
                    LessonDAO lessonDAO = new LessonDAO();
                    try {
                        lessonDAO.addLesson(title, "", skill, content, difficulty, "");
                        Toast.makeText(ManageAddLessonActivity.this, "Add new Lesson Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult(202,intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
