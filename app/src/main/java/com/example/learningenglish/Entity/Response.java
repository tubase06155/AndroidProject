package com.example.learningenglish.Entity;

import java.io.Serializable;

public class Response implements Serializable {
 private    String name, email, title, content;
    private   boolean isActive;
    private  int id;

    public Response(int id, String name, String email, String title, String content, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.title = title;
        this.content = content;
        this.isActive = isActive;
    }

    public Response() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
