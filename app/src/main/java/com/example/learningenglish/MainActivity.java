package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button button;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
            button = findViewById(R.id.buttonlogin);
           final LoginDAO db = new LoginDAO();

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String info  = db.Login(username.getText().toString(),password.getText().toString());
                    Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
                }
            });

        }
}
