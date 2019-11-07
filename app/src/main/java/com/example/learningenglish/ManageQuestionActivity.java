package com.example.learningenglish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        setQessonAdapter();
        registerForContextMenu(listViewManageQuestion);

    }
    public void setQessonAdapter(){

        try {
            QuestionDAO questionDAO = new QuestionDAO();
            listQuestion = questionDAO.listQuestion();
            questionAdapter = new QuestionAdapter(this, R.layout.manage_question_layout, listQuestion);
            listViewManageQuestion.setAdapter(questionAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit Question");
        menu.add("Delete Question");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Delete Question")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            final Question question = (Question) listViewManageQuestion.getAdapter().getItem(position);
            AlertDialog alertDialog = null;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure to delete the question ? \n");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    QuestionDAO questionDAO = new QuestionDAO();
                    try {
                        questionDAO.deleteByID(question.getQuestionID()+"");
                        setQessonAdapter();
                        Toast.makeText(getApplicationContext(), "Delete this question successfully", Toast.LENGTH_SHORT).show();
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
        else if (item.getTitle().equals("Edit Question")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            Question question = (Question) listViewManageQuestion.getAdapter().getItem(position);

            Intent intent = new Intent(this,ManageQuestionEditingActivity.class);
            intent.putExtra("question",question);
            startActivityForResult(intent,104);

        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 104 && resultCode == 204) {
            setQessonAdapter();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
