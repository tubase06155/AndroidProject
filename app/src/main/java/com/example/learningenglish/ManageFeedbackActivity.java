package com.example.learningenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learningenglish.Entity.Response;
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.adapter.FeedbackAdapter;
import com.example.learningenglish.adapter.UserAdapter;
import com.example.learningenglish.dal.ResponseDAO;
import com.example.learningenglish.dal.UserDAO;

import java.util.List;

public class ManageFeedbackActivity extends AppCompatActivity {
    ListView listViewManageFeedback;

    FeedbackAdapter feedbackAdapter;
    List<Response> listFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_feedback);
        listViewManageFeedback = findViewById(R.id.listViewManageFeedback);
        setFeedbackAdapter();
        registerForContextMenu(listViewManageFeedback);
    }

    public void setFeedbackAdapter(){
        try {
            ResponseDAO responseDAO = new ResponseDAO();
            listFeedback = responseDAO.listAllResponse();
            feedbackAdapter = new FeedbackAdapter(this, R.layout.manage_feedback_layout, listFeedback);
            listViewManageFeedback.setAdapter(feedbackAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Detail Feedback");
        menu.add("Delete Feedback");


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Delete Feedback")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            final Response feedback = (Response) listViewManageFeedback.getAdapter().getItem(position);
            AlertDialog alertDialog = null;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure to delete the feedback ? \n");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ResponseDAO responseDAO = new ResponseDAO();
                    try {
                        responseDAO.deleteByID(feedback.getId() + "");
                        setFeedbackAdapter();
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
            alertDialog = builder.create();
            alertDialog.show();


        }
        else   if (item.getTitle().equals("Detail Feedback")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            final Response feedback = (Response) listViewManageFeedback.getAdapter().getItem(position);
            AlertDialog alertDialog = null;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Feedback's content \n");
          builder.setMessage(feedback.getContent());
            alertDialog = builder.create();
            alertDialog.show();


        }
        return super.onContextItemSelected(item);
    }
}
