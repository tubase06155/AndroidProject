package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learningenglish.dal.UserDAO;

public class RegisterActivity extends AppCompatActivity {
     EditText registerUsername, registerEmail,registerPassword,registerRePassword;
     Button registerSubmit;
     String username, email, password, rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerUsername = findViewById(R.id.registerUsername);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        registerRePassword = findViewById(R.id.registerRePassword);
        registerSubmit = findViewById(R.id.registerSubmit);
        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = registerUsername.getText().toString();
                email = registerEmail.getText().toString();
                password = registerPassword.getText().toString();
                rePassword = registerRePassword.getText().toString();
                UserDAO userDAO = new UserDAO();
                try {
                if (username.trim().equals("") || email.trim().equals("")|| password.trim().equals("")|| rePassword.trim().equals("")  ){
                    Toast.makeText(getApplicationContext(),"please enter all field in form ",Toast.LENGTH_SHORT).show();

                } else if (!password.equals(rePassword)){
                    Toast.makeText(getApplicationContext(),"Password must be equal Retyping password ",Toast.LENGTH_SHORT).show();

                } else if (userDAO.isUsernameExisted(username)){
                    Toast.makeText(getApplicationContext(),"Username  " + username + " has been existed, please enter other username",Toast.LENGTH_SHORT).show();

                } else {
                    userDAO.registerUser(username,password,email);
                    Toast.makeText(getApplicationContext(),"Register successfully",Toast.LENGTH_SHORT).show();
          finish();

                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
