package com.example.learningenglish.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learningenglish.Entity.Response;
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.R;

import java.util.List;

public class FeedbackAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    List<Response> listFeedback;


    public FeedbackAdapter(Activity activity, int layout, List<Response> listFeedback) {
        this.activity = activity;
        this.layout = layout;
        this.listFeedback = listFeedback;
    }

    @Override
    public int getCount() {
        return listFeedback.size();
    }

    @Override
    public Object getItem(int i) {
        return listFeedback.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView manageFeedbackName, manageFeedbackTitle, manageFeedbackContact;
        ImageView imageView;
        int[]  imgs = {R.drawable.ronaldo,R.drawable.morinho, R.drawable.messi};
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            manageFeedbackName = view.findViewById(R.id.manageFeedbackName);
            manageFeedbackTitle = view.findViewById(R.id.manageFeedbackTitle);
            manageFeedbackContact = view.findViewById(R.id.manageFeedbackContact);
            imageView = view.findViewById(R.id.imageViewFeedBack);

            view.setTag(R.id.manageFeedbackName, manageFeedbackName);
            view.setTag(R.id.manageFeedbackTitle, manageFeedbackTitle);
            view.setTag(R.id.manageFeedbackContact, manageFeedbackContact);
            view.setTag(R.id.imageViewFeedBack,imageView);


        } else {
            manageFeedbackName = (TextView) view.getTag(R.id.manageFeedbackName);
            manageFeedbackTitle = (TextView) view.getTag(R.id.manageFeedbackTitle);
            manageFeedbackContact = (TextView) view.getTag(R.id.manageFeedbackContact);
            imageView = (ImageView) view.getTag(R.id.imageViewFeedBack);

        }
        final  Response feedback = listFeedback.get(i);
        manageFeedbackName.setText(feedback.getName());
        manageFeedbackTitle.setText(feedback.getTitle());

        manageFeedbackContact.setText(feedback.getEmail());
        if(i<=2)imageView.setImageResource(imgs[i]);

        else imageView.setImageResource(R.drawable.rankicon2);

        return view;
    }
}
