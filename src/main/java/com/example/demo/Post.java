package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
public class Post {

    @FXML
    Label created;
    @FXML
    Label priv;
    @FXML
    Label caption;
    private long post_ID;
    private LocalDate post_created_on;
    private String  post_caption;
    private int post_reacts;
    //private User [] users_tagged_in_post = new User[10000];
    private Comment[] comments_on_post = new Comment[10000];
    private String post_privacy;

    public void setPost_reacts(int post_reacts) {
        this.post_reacts = post_reacts;
    }

    public void setComments_on_post(Comment[] comments_on_post) {
        this.comments_on_post = comments_on_post;
    }

    public void setPost_caption(String post_caption) {
        this.post_caption = post_caption;
    }

    public void setPost_ID(long post_ID) {
        this.post_ID = post_ID;
    }

    public void setPost_privacy(String post_privacy) {
        this.post_privacy = post_privacy;
    }

    public void setPost_created_on(LocalDate post_created_on) {
        this.post_created_on = post_created_on;
    }

    public int getPost_reacts() {
        return post_reacts;
    }

    public Comment[] getComments_on_post() {
        return comments_on_post;
    }

    public String getPost_caption() {
        return post_caption;
    }

    public long getPost_ID() {
        return post_ID;
    }

    public String getPost_privacy() {
        return post_privacy;
    }

    public LocalDate getPost_created_on() {
        return post_created_on;
    }


    public void displayprivacy(Post post)
    {
        priv.setText(post.post_privacy);
    }
    public void displaycaption(Post post)
    {
        caption.setText(post.post_caption);
    }


}
