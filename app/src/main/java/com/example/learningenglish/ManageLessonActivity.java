package com.example.learningenglish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.Response;
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.adapter.LessonAdapter;
import com.example.learningenglish.adapter.ManageLessonAdapter;
import com.example.learningenglish.adapter.UserAdapter;
import com.example.learningenglish.dal.LessonDAO;
import com.example.learningenglish.dal.ResponseDAO;
import com.example.learningenglish.dal.UserDAO;

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
       setLessonAdapter();
        registerForContextMenu(listViewManageLesson);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add new lesson");
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Add new lesson")) {
            Intent intent = new Intent(this, ManageAddLessonActivity.class);
            startActivityForResult(intent, 102);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit Lesson");
        menu.add("Delete Lesson");
        menu.add("Add Question");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Delete Lesson")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            final Lesson lesson = (Lesson) listViewManageLesson.getAdapter().getItem(position);
            AlertDialog alertDialog = null;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure to delete the lesson ? \n");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LessonDAO lessonDAO = new LessonDAO();
                    try {
                        lessonDAO.deleteByID(lesson.getLessonID()+"");
                        setLessonAdapter();
                        Toast.makeText(ManageLessonActivity.this, "Delete this lesson successfully", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ///chcvhcvhcvjcvj
            alertDialog = builder.create();
            alertDialog.show();


        }
        else if (item.getTitle().equals("Edit Lesson")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            Lesson lesson = (Lesson) listViewManageLesson.getAdapter().getItem(position);

            Intent intent = new Intent(this,ManageLessonEditingActivity.class);
            intent.putExtra("lesson",lesson);
            startActivityForResult(intent,103);



        }
        else if (item.getTitle().equals("Add Question")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            Lesson lesson = (Lesson) listViewManageLesson.getAdapter().getItem(position);

            Intent intent = new Intent(this,ManageAddQuestionActivity.class);
            intent.putExtra("lesson",lesson);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }
    public void setLessonAdapter(){

        try {
            LessonDAO lessonDAO = new LessonDAO();
            listLesson = lessonDAO.listAllLesson();
            manageLessonAdapter = new ManageLessonAdapter(this, R.layout.mangae_lesson_layout, listLesson);
            listViewManageLesson.setAdapter(manageLessonAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 102 && resultCode == 202) {
            setLessonAdapter();
        }
        if (requestCode == 103 && resultCode == 203) {
            setLessonAdapter();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
