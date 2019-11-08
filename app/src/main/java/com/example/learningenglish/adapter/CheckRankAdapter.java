package com.example.learningenglish.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learningenglish.Entity.User;
import com.example.learningenglish.R;

import java.util.List;

public class CheckRankAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    List<User> listUser;

    public CheckRankAdapter(Activity activity, int layout, List<User> listUser) {
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
        ImageView imageView;
        int[]  imgs = {R.drawable.icon1st,R.drawable.icon2nd,R.drawable.icon3rd};
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            manageUsername = view.findViewById(R.id.manageUsername2);
            manageUserEmail = view.findViewById(R.id.manageUserEmail2);
            manageUserScore = view.findViewById(R.id.manageUserScore2);
            imageView = view.findViewById(R.id.imageUser2);

            view.setTag(R.id.manageUsername, manageUsername);
            view.setTag(R.id.manageUserEmail, manageUserEmail);
            view.setTag(R.id.manageUserScore, manageUserScore);
            view.setTag(R.id.imageUser2,imageView);


        } else {
            manageUsername = (TextView) view.getTag(R.id.manageUsername);
            manageUserEmail = (TextView) view.getTag(R.id.manageUserEmail);
            manageUserScore = (TextView) view.getTag(R.id.manageUserScore);
            imageView = (ImageView) view.getTag(R.id.imageUser2);

        }
        final  User user = listUser.get(i);
        manageUsername.setText(user.getUsername());
        manageUserEmail.setText(user.getEmail());
        manageUserScore.setText("Score : " + user.getScore());

        if(i<=2){

            imageView.setImageResource(imgs[i]);
        }
        if(i>2)imageView.setImageResource(R.drawable.user);



        return view;
    }

}
