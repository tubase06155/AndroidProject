package com.example.learningenglish.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.R;

import java.util.List;

public class LessonAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    List<Lesson> listLesson;

    public LessonAdapter(Activity activity, int layout, List<Lesson> listLesson) {
        this.activity = activity;
        this.layout = layout;
        this.listLesson = listLesson;
    }

    @Override
    public int getCount() {
        return listLesson.size();
    }

    @Override
    public Object getItem(int i) {
        return listLesson.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView lessonTitle, lessonDifficult;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            lessonTitle = view.findViewById(R.id.lessonTitle);
            lessonDifficult = view.findViewById(R.id.lessonDifficult);

            view.setTag(R.id.lessonTitle, lessonTitle);
            view.setTag(R.id.lessonDifficult, lessonDifficult);


        } else {
            lessonTitle = (TextView) view.getTag(R.id.lessonTitle);
            lessonDifficult = (TextView) view.getTag(R.id.lessonDifficult);

        }
        final  Lesson lesson = listLesson.get(i);
        lessonTitle.setText(lesson.getTitle());
        lessonDifficult.setText("Difficulty: " + lesson.getDifficulty()+ "/5");
        return view;
    }
}
