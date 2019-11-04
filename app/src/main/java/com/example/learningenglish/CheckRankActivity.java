package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.learningenglish.Entity.User;
import com.example.learningenglish.adapter.UserAdapter;
import com.example.learningenglish.dal.UserDAO;

import java.util.List;

public class CheckRankActivity extends AppCompatActivity {
    ListView listViewCheckRank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_rank);
        listViewCheckRank = findViewById(R.id.listViewCheckRank);

        UserDAO userDAO = new UserDAO();
        try {
            List<User> listUser = userDAO.getListOfRank();
         UserAdapter   userAdapter = new UserAdapter(this, R.layout.manage_user_layout, listUser);
            listViewCheckRank.setAdapter(userAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
