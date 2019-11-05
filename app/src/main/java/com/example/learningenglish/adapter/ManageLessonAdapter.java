package com.example.learningenglish.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.R;

import java.util.List;

public class ManageLessonAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    List<Lesson> listLesson;

    public ManageLessonAdapter(Activity activity, int layout, List<Lesson> listLesson) {
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
        TextView manageLessonTitle, manageLessonSkill,manageLessonDiffulty;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            manageLessonTitle = view.findViewById(R.id.manageLessonTitle);
            manageLessonSkill = view.findViewById(R.id.manageLessonSkill);
            manageLessonDiffulty = view.findViewById(R.id.manageLessonDiffulty);

            view.setTag(R.id.manageLessonTitle, manageLessonTitle);
            view.setTag(R.id.manageLessonSkill, manageLessonSkill);
            view.setTag(R.id.manageLessonDiffulty, manageLessonDiffulty);


        } else {
            manageLessonTitle = (TextView) view.getTag(R.id.manageLessonTitle);
            manageLessonSkill = (TextView) view.getTag(R.id.manageLessonSkill);
            manageLessonDiffulty = (TextView) view.getTag(R.id.manageLessonDiffulty);

        }
        final  Lesson lesson = listLesson.get(i);
        manageLessonTitle.setText(lesson.getTitle());
        manageLessonDiffulty.setText("Difficulty: " + lesson.getDifficulty()+ "/5");
        if (lesson.getCategory() == 1)
        manageLessonSkill.setText("Skill: Reading");
        else   if (lesson.getCategory() == 3)
            manageLessonSkill.setText("Skill: Grammar");
        return view;
    }
}
