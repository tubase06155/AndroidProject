package com.example.learningenglish.Entity;

import java.io.Serializable;

public class User  implements Serializable {
    private int userID;
    private String username;
    private String password;
    private String email;
    private int userType;
    private int score;
    private boolean isActive;

    public User() {
    }

    public User(int userID, String username, String password, String email, int userType, int score, boolean isActive) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.score = score;
        this.isActive = isActive;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
