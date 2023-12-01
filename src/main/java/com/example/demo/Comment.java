package com.example.demo;

import java.time.LocalDate;

public class Comment {

    private long comment_ID;
    private long post_ID;
    private long user_ID;
    private String [] comment_content = new String[10000];
    //private User [] comment_react = new User[10000];
    private LocalDate comment_created_on;
    private Comment [] comment_reply = new Comment[10000];

    public void setComment_ID(long comment_ID) {
        this.comment_ID = comment_ID;
    }

    public void setComment_content(String[] comment_content) {
        this.comment_content = comment_content;
    }

    public void setComment_created_on(LocalDate comment_created_on) {
        this.comment_created_on = comment_created_on;
    }

    public void setComment_reply(Comment[] comment_reply) {
        this.comment_reply = comment_reply;
    }

    public long getPost_ID() {
        return post_ID;
    }

    public Comment[] getComment_reply() {
        return comment_reply;
    }

    public LocalDate getComment_created_on() {
        return comment_created_on;
    }

    public long getComment_ID() {
        return comment_ID;
    }

    public long getUser_ID() {
        return user_ID;
    }

    public String[] getComment_content() {
        return comment_content;
    }

    void addComment(Comment comment) {
        this.comment_content=comment.comment_content;
        this.user_ID=comment.user_ID;
        this.comment_created_on=comment.comment_created_on;
        comment_ID++;

    }


}
