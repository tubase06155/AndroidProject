package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.adapter.LessonAdapter;
import com.example.learningenglish.adapter.ManageLessonAdapter;
import com.example.learningenglish.adapter.UserAdapter;
import com.example.learningenglish.dal.LessonDAO;

import java.util.List;

public class ManageLessonActivity extends AppCompatActivity {
    ListView listViewManageLesson;
    ManageLessonAdapter manageLessonAdapter;
    List<Lesson> listLesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_lesson);


        listViewManageLesson = findViewById(R.id.listViewManageLesson);
        LessonDAO lessonDAO = new LessonDAO();
        try {
            listLesson = lessonDAO.listAllLesson();
            manageLessonAdapter = new ManageLessonAdapter(this, R.layout.mangae_lesson_layout, listLesson);
            listViewManageLesson.setAdapter(manageLessonAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerForContextMenu(listViewManageLesson);
    }
}
