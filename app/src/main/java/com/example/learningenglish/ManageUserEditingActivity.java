package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.learningenglish.Entity.User;
import com.example.learningenglish.dal.UserDAO;

public class ManageUserEditingActivity extends AppCompatActivity {

    EditText editUsername,editEmail,editScore;
    RadioButton rdUser,rdAdmin;
    CheckBox checkBoxActive;
    Button editUserSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_editing);
        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editScore = findViewById(R.id.editScore);
        rdUser = findViewById(R.id.rdUser);
        rdAdmin = findViewById(R.id.rdAdmin);
        editUserSave = findViewById(R.id.editUserSave);
        Intent intent = getIntent();
       final User user =(User)intent.getSerializableExtra("user");
        editUsername.setText(user.getUsername());
        editEmail.setText(user.getEmail());
        editScore.setText(user.getScore() + "");

      if (user.getUserType() == 1)  rdUser.setChecked(true); else rdAdmin.setChecked(true);

        editUserSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editEmail.getText().toString();
                int score = Integer.parseInt(editScore.getText().toString());
                int userType = (rdUser.isChecked()) ? 1 : 2 ;

                UserDAO userDAO = new UserDAO();
                try {
                    userDAO.updateUserInfo(user.getUserID() + "",email,userType+"",score + "");
                    Intent intent = new Intent();
                    setResult(201,intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
