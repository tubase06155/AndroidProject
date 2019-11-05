package com.example.learningenglish.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.Question;
import com.example.learningenglish.R;

import java.util.List;

public class QuizAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    List<Question> listQuestion;

    public QuizAdapter(Activity activity, int layout, List<Question> listQuestion) {
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
        TextView quizContent;
        RadioButton quizOpt1, quizOpt2, quizOpt3, quizOpt4;

        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            quizContent = view.findViewById(R.id.quizContent);
            quizOpt1 = view.findViewById(R.id.quizOpt1);
            quizOpt2 = view.findViewById(R.id.quizOpt2);
            quizOpt3 = view.findViewById(R.id.quizOpt3);
            quizOpt4 = view.findViewById(R.id.quizOpt4);

            view.setTag(R.id.quizContent, quizContent);
            view.setTag(R.id.quizOpt1, quizOpt1);
            view.setTag(R.id.quizOpt2, quizOpt2);
            view.setTag(R.id.quizOpt3, quizOpt3);
            view.setTag(R.id.quizOpt4, quizOpt4);


        } else {
            quizContent = (TextView) view.getTag(R.id.quizContent);
            quizOpt1 = (RadioButton) view.getTag(R.id.quizOpt1);
            quizOpt2 = (RadioButton) view.getTag(R.id.quizOpt2);
            quizOpt3 = (RadioButton) view.getTag(R.id.quizOpt3);
            quizOpt4 = (RadioButton) view.getTag(R.id.quizOpt4);

        }
        final  Question question = listQuestion.get(i);
        quizContent.setText(question.getContent());
        quizOpt1.setText(question.getOpt1());
        quizOpt2.setText(question.getOpt2());
        quizOpt3.setText(question.getOpt3());
        quizOpt4.setText(question.getOpt4());


        return view;
    }
}
