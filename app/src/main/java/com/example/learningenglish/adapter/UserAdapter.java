package com.example.learningenglish.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.learningenglish.Entity.User;
import com.example.learningenglish.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    List<User> listUser;

    public UserAdapter(Activity activity, int layout, List<User> listUser) {
        this.activity = activity;
        this.layout = layout;
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int i) {
        return listUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView manageUsername, manageUserEmail, manageUserScore;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            manageUsername = view.findViewById(R.id.manageUsername);
            manageUserEmail = view.findViewById(R.id.manageUserEmail);
            manageUserScore = view.findViewById(R.id.manageUserScore);

            view.setTag(R.id.manageUsername, manageUsername);
            view.setTag(R.id.manageUserEmail, manageUserEmail);
            view.setTag(R.id.manageUserScore, manageUserScore);


        } else {
            manageUsername = (TextView) view.getTag(R.id.manageUsername);
            manageUserEmail = (TextView) view.getTag(R.id.manageUserEmail);
            manageUserScore = (TextView) view.getTag(R.id.manageUserScore);

        }
        final  User user = listUser.get(i);
        manageUsername.setText(user.getUsername());
        manageUserEmail.setText(user.getEmail());
        manageUserScore.setText("Score : " + user.getScore());

        return view;
    }

}
