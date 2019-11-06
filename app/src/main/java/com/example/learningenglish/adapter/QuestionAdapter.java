package com.example.learningenglish.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.Question;
import com.example.learningenglish.R;

import java.util.List;

public class QuestionAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    List<Question> listQuestion;

    public QuestionAdapter(Activity activity, int layout, List<Question> listQuestion) {
        this.activity = activity;
        this.layout = layout;
        this.listQuestion = listQuestion;
    }

    @Override
    public int getCount() {
        return listQuestion.size();
    }

    @Override
    public Object getItem(int i) {
        return listQuestion.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView manageQuestionLesson, manageQuestionContent;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            manageQuestionLesson = view.findViewById(R.id.manageQuestionLesson);
            manageQuestionContent = view.findViewById(R.id.manageQuestionContent);
            //manageLessonDiffulty = view.findViewById(R.id.manageLessonDiffulty);

            view.setTag(R.id.manageQuestionLesson, manageQuestionLesson);
            view.setTag(R.id.manageQuestionContent, manageQuestionContent);
          //  view.setTag(R.id.manageLessonDiffulty, manageLessonDiffulty);


        } else {
            manageQuestionLesson = (TextView) view.getTag(R.id.manageQuestionLesson);
            manageQuestionContent = (TextView) view.getTag(R.id.manageQuestionContent);
            //manageLessonDiffulty = (TextView) view.getTag(R.id.manageLessonDiffulty);

        }
        final  Question question = listQuestion.get(i);
        try {
            manageQuestionLesson.setText(question.getLesson().getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
        manageQuestionContent.setText("Content: " + question.getContent());

        return view;
    }
}
