package com.example.learningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningenglish.Entity.Lesson;
import com.example.learningenglish.Entity.Question;
import com.example.learningenglish.Entity.User;
import com.example.learningenglish.adapter.QuizAdapter;
import com.example.learningenglish.dal.LessonDAO;
import com.example.learningenglish.dal.QuestionDAO;
import com.example.learningenglish.dal.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class SmallQuizActivity extends AppCompatActivity {
    TextView smallQuizContent,smallQuizIndex;
    RadioButton smallQuizAns1,smallQuizAns2,smallQuizAns3,smallQuizAns4;
    Button smallQuizFinish, smallQuizNext,smallQuizPrevious;
    List<Question> listQuestion = new ArrayList<>();
    User user;
    List<Integer> rightOpt = new ArrayList<Integer>();
    List<Integer> chooseOpt = new ArrayList<Integer>();
    int numberRightQuestion = 0;
    int index = 0;
    int showAnswer = 0;
    int currentScore = 0;
    Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_quiz);
        smallQuizContent = findViewById(R.id.smallQuizContent);
        smallQuizIndex = findViewById(R.id.smallQuizIndex);
        smallQuizAns1 = findViewById(R.id.smallQuizAns1);
        smallQuizAns2 = findViewById(R.id.smallQuizAns2);
        smallQuizAns3 = findViewById(R.id.smallQuizAns3);
        smallQuizAns4 = findViewById(R.id.smallQuizAns4);
        smallQuizFinish = findViewById(R.id.smallQuizFinish);
        smallQuizNext = findViewById(R.id.smallQuizNext);
        smallQuizPrevious = findViewById(R.id.smallQuizPrevious);


        Intent intent = getIntent();
         lesson = (Lesson) intent.getSerializableExtra("lesson");
         user = (User) intent.getSerializableExtra("user");

        try {
            listQuestion = new QuestionDAO().listQuestionByLessonID(lesson.getLessonID() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (listQuestion.size() > 0){
            smallQuizIndex.setText(index+1+". ");
            smallQuizContent.setText(listQuestion.get(0).getContent());
            smallQuizAns1.setText(listQuestion.get(0).getOpt1());
            smallQuizAns2.setText(listQuestion.get(0).getOpt2());
            smallQuizAns3.setText(listQuestion.get(0).getOpt3());
            smallQuizAns4.setText(listQuestion.get(0).getOpt4());

            for(int i = 0; i <listQuestion.size();i++){
                rightOpt.add(listQuestion.get(i).getRightOpt());
                chooseOpt.add(0);
            }
            loadChooseAnswer();





        } else {
            Toast.makeText(this, "This Lesson has not any questions, please do quiz the other lesson", Toast.LENGTH_SHORT).show();
            finish();
        }
        actionForNextClick();
        actionForPreviousClick();
        actionForFinishClick();

    }
    public void action_smallQuizAns1_click(View view){
     chooseOpt.set(index,1);
    }
    public void action_smallQuizAns2_click(View view){
        chooseOpt.set(index,2);

    }
    public void action_smallQuizAns3_click(View view){
        chooseOpt.set(index,3);

    }
    public void action_smallQuizAns4_click(View view){
        chooseOpt.set(index,4);

    }

    private  void actionForPreviousClick(){
        smallQuizPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (showAnswer == 0){
                if (index > 1 ) {
                    index--;
                    smallQuizIndex.setText(index+1+". ");
                    smallQuizContent.setText(listQuestion.get(index).getContent());
                    smallQuizAns1.setText(listQuestion.get(index).getOpt1());
                    smallQuizAns2.setText(listQuestion.get(index).getOpt2());
                    smallQuizAns3.setText(listQuestion.get(index).getOpt3());
                    smallQuizAns4.setText(listQuestion.get(index).getOpt4());
                    smallQuizNext.setEnabled(true);
                } else if (index == 1){
                    smallQuizPrevious.setEnabled(false);
                    smallQuizNext.setEnabled(true);
                    index--;
                    smallQuizIndex.setText(index+1+". ");
                    smallQuizContent.setText(listQuestion.get(index).getContent());
                    smallQuizAns1.setText(listQuestion.get(index).getOpt1());
                    smallQuizAns2.setText(listQuestion.get(index).getOpt2());
                    smallQuizAns3.setText(listQuestion.get(index).getOpt3());
                    smallQuizAns4.setText(listQuestion.get(index).getOpt4());
                }
                loadChooseAnswer();
               // Toast.makeText(getApplicationContext(),index+"",Toast.LENGTH_SHORT).show();

            }
            }
        });
    }
    private  void actionForNextClick(){
        smallQuizNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (showAnswer == 0){
                    if (index < listQuestion.size() -2){
                        index++;
                        smallQuizIndex.setText(index+1+". ");
                        smallQuizContent.setText(listQuestion.get(index).getContent());
                        smallQuizAns1.setText(listQuestion.get(index).getOpt1());
                        smallQuizAns2.setText(listQuestion.get(index).getOpt2());
                        smallQuizAns3.setText(listQuestion.get(index).getOpt3());
                        smallQuizAns4.setText(listQuestion.get(index).getOpt4());
                        smallQuizPrevious.setEnabled(true);
                    } else if (index == listQuestion.size()-2){
                        smallQuizNext.setEnabled(false);
                        smallQuizPrevious.setEnabled(true);
                        index++;
                        smallQuizIndex.setText(index+1+". ");
                        smallQuizContent.setText(listQuestion.get(index).getContent());
                        smallQuizAns1.setText(listQuestion.get(index).getOpt1());
                        smallQuizAns2.setText(listQuestion.get(index).getOpt2());
                        smallQuizAns3.setText(listQuestion.get(index).getOpt3());
                        smallQuizAns4.setText(listQuestion.get(index).getOpt4());

                    }
                    loadChooseAnswer();
                   // Toast.makeText(getApplicationContext(),index+"",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void actionForFinishClick(){
        smallQuizFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = null;
                AlertDialog.Builder builder = new AlertDialog.Builder(SmallQuizActivity.this);
                builder.setTitle("Are you sure to finish the quiz ? \n");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < listQuestion.size(); j++)
                        {
                            if (chooseOpt.get(j) == rightOpt.get(j))
                            {
                                numberRightQuestion++;

                            }
                        }
                      //  smallQuizFinish.setEnabled(false);

                       // Toast.makeText(getApplicationContext(),numberRightQuestion+"/" + chooseOpt.size(),Toast.LENGTH_SHORT).show();

                        try {
                             currentScore = new UserDAO().getCurrentScoreByUserID(user.getUserID());
                            new UserDAO().updateScoreByUserID(user.getUserID(),currentScore,numberRightQuestion*10);
                            currentScore = new UserDAO().getCurrentScoreByUserID(user.getUserID());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        AlertDialog alertDialog = null;
                        AlertDialog.Builder builder = new AlertDialog.Builder(SmallQuizActivity.this);
                        builder.setTitle("Result \n");
                        builder.setMessage("You got " + numberRightQuestion + "/" + listQuestion.size() + "points \n"  +
                                "Your score now is " + currentScore + "\n" +
                                "Do you want to do quiz again ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(SmallQuizActivity.this,SmallQuizActivity.class);

                                intent.putExtra("user",user);
                                intent.putExtra("lesson",lesson);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(SmallQuizActivity.this,UserActivity.class);
                                user.setScore(currentScore);
                                intent.putExtra("user",user);
                                //intent.putExtra("lesson",lesson);
                                startActivity(intent);
                            }
                        });
                        alertDialog = builder.create();
                        alertDialog.show();






                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ///chcvhcvhcvjcvj
                alertDialog = builder.create();
                alertDialog.show();


            }
        });
    }


    private  void loadChooseAnswer(){
        smallQuizAns1.setChecked(false);
        smallQuizAns2.setChecked(false);
        smallQuizAns3.setChecked(false);
        smallQuizAns4.setChecked(false);
        if (chooseOpt.get(index) == 1){
            smallQuizAns1.setChecked(true);
        } else   if (chooseOpt.get(index) == 2){
            smallQuizAns2.setChecked(true);
        } else  if (chooseOpt.get(index) == 3){
            smallQuizAns3.setChecked(true);
        } else  if (chooseOpt.get(index) == 4){
            smallQuizAns4.setChecked(true);
        }


    }
}
