package com.example.learningenglish.Entity;

import java.io.Serializable;

public class Lesson implements Serializable {
   private int lessonID;
    private   String title;
    private String imagePath;
    private int category;
    private  String content;
    private int difficulty;
    private String audioPath;

    public Lesson() {
    }

    public Lesson(int lessonID, String title, String imagePath, int category, String content, int difficulty, String audioPath) {
        this.lessonID = lessonID;
        this.title = title;
        this.imagePath = imagePath;
        this.category = category;
        this.content = content;
        this.difficulty = difficulty;
        this.audioPath = audioPath;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }
}
