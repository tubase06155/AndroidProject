package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learningenglish.Entity.User;
import com.example.learningenglish.dal.UserDAO;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button buttonLogin,buttonRegister;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            username = findViewById(R.id.loginUsername);
            password = findViewById(R.id.loginPassword);
            buttonLogin = findViewById(R.id.buttonLogin);
            buttonRegister = findViewById(R.id.buttonRegister);


            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    UserDAO userDAO = new UserDAO();
                    try {
                        if (userDAO.isValidUsernameAndPassword(username.getText().toString(),password.getText().toString())){

                        Toast.makeText(getApplicationContext(),"login successfuully",Toast.LENGTH_SHORT).show();
                            User user = userDAO.selectUserByUsername(username.getText().toString());
                            if (user.getUserType() == 2) {
                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                startActivity(intent);
                            } else if (user.getUserType() == 1){

                            }

                        }
                        else  Toast.makeText(getApplicationContext(),"login failed",Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            });

        }
}
