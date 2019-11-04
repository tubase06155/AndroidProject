package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learningenglish.Entity.User;
import com.example.learningenglish.dal.ResponseDAO;

public class FeedbackActivity extends AppCompatActivity {
    EditText feedBackName, feedBackEmail, feedBackTitle, feedBackContent;
    Button feedBackSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedBackName = findViewById(R.id.feedBackName);
        feedBackEmail = findViewById(R.id.feedBackEmail);
        feedBackTitle = findViewById(R.id.feedBackTitle);
        feedBackContent = findViewById(R.id.feedBackContent);
        feedBackSubmit = findViewById(R.id.feedBackSubmit);

        Intent intent = getIntent();
        final User user =(User)intent.getSerializableExtra("user");
        feedBackName.setText(user.getUsername());
        feedBackEmail.setText(user.getEmail());

        feedBackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = feedBackName.getText().toString();
                String email = feedBackEmail.getText().toString();
                String title = feedBackTitle.getText().toString();
                String content = feedBackContent.getText().toString();
                if (name.trim().equals("") || email.trim().equals("") || title.trim().equals("") || content.trim().equals("") ){
                    Toast.makeText(getApplicationContext(), "Send feedback failed, please enter all field ", Toast.LENGTH_SHORT).show();
                } else {
                    ResponseDAO responseDAO = new ResponseDAO();
                    try {
                        responseDAO.addResponse(name,title,content,1,true,email);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Send feedback successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
