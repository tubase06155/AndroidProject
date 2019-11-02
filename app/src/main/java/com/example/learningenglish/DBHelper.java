package com.example.learningenglish;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableUser = "CREATE TABLE User(" +
                "userId integer primary key autoincrement," +
                "username text,"+
                "password text," +
                "email text,"+
                "userType integer,"+
                "score integer,"+
                "isActive boolean)";
        sqLiteDatabase.execSQL(createTableUser);

        String createTableFeedback = "CREATE TABLE Feedback(" +
                "feedbackId integer primary key autoincrement," +
                "username text," +
                "title text,"+
                "email integer,"+
                "content text,"+
                "isActive boolean)";
        sqLiteDatabase.execSQL(createTableFeedback);

        String createTableSkill = "CREATE TABLE Skill(" +
                "skillId integer primary key autoincrement," +
                "skillName text)";
        sqLiteDatabase.execSQL(createTableSkill);

        String createTableLesson = "CREATE TABLE Lesson(" +
                "lessonId integer primary key autoincrement," +
                "skill integer,"+
                "imagePath text," +
                "audioPath text," +
                "title text,"+
                "difficulty integer,"+
                "content text,"+
                "isActive boolean)";
        sqLiteDatabase.execSQL(createTableLesson);

        String createTableQuestion = "CREATE TABLE Question(" +
                "questionID integer primary key autoincrement," +
                "lessonID integer,"+
                "questionContent text," +
                "opt1 text," +
                "opt2 text,"+
                "opt3 text,"+
                "opt4 text,"+
                "rightOption integer,"+
                "isActive boolean)";
        sqLiteDatabase.execSQL(createTableQuestion);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
