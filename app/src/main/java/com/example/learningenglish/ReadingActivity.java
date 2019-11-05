package com.example.learningenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.adapter.LessonAdapter;
import com.example.learningenglish.dal.LessonDAO;

import java.util.List;

public class ReadingActivity extends AppCompatActivity {
    ListView listViewReadingLesson;
    LessonAdapter lessonAdapter;
    List<Lesson> listLesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        listViewReadingLesson = findViewById(R.id.listViewReadingLesson);
        LessonDAO lessonDAO = new LessonDAO();
        try {
            listLesson = lessonDAO.listLessonByCategory("1");
            lessonAdapter = new LessonAdapter(this, R.layout.lesson_layout, listLesson);
            listViewReadingLesson.setAdapter(lessonAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerForContextMenu(listViewReadingLesson);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Learn Now");
        menu.add("Do Quiz");

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Learn Now")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            Lesson lesson = (Lesson) listViewReadingLesson.getAdapter().getItem(position);
            Intent intent = new Intent(this,LearnLessonActivity.class);
            intent.putExtra("lesson",lesson);
            startActivity(intent);
        }
        else if (item.getTitle().equals("Do Quiz")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            Lesson lesson = (Lesson) listViewReadingLesson.getAdapter().getItem(position);
            Intent intent1 = getIntent();
            User user = (User) intent1.getSerializableExtra("user");

            Intent intent = new Intent(this,DoQuizActivity.class);

            intent.putExtra("user",user);
            intent.putExtra("lesson",lesson);
            startActivity(intent);
        }

        return super.onContextItemSelected(item);
    }
}
