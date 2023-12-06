package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Post {
    private int post_ID;
    private LocalDate post_created_on;
    private String post_caption;
    private int reacts_counter;
    //private User [] users_tagged_in_post = new User[10000];
    private ArrayList<Comment> comments_on_post = new ArrayList<>();
    private String post_privacy;

    Post(int post_ID, String post_caption, LocalDate post_created_on, String post_privacy) {
        setPost_ID(post_ID);
        setPost_caption(post_caption);
        setPost_created_on(post_created_on);
        setPost_privacy(post_privacy);
    }

    public void setPost_reacts(int reacts_counter) {
        this.reacts_counter = reacts_counter;
    }
    public int getPost_reacts() {
        return reacts_counter;
    }

    public void setComments_on_post(ArrayList<Comment> comments_on_post) {
        this.comments_on_post = comments_on_post;
    }
    public ArrayList<Comment> getComments_on_post() {
        return comments_on_post;
    }

    public void setPost_caption(String post_caption) {
        this.post_caption = post_caption;
    }
    public String getPost_caption() {
        return post_caption;
    }

    public void setPost_ID(int post_ID) {
        this.post_ID = post_ID;
    }
    public long getPost_ID() {
        return post_ID;
    }

    public void setPost_privacy(String post_privacy) {
        this.post_privacy = post_privacy;
    }
    public String getPost_privacy() {
        return post_privacy;
    }

    public void setPost_created_on(LocalDate post_created_on) {
        this.post_created_on = post_created_on;
    }

    public String getPost_created_on() {
        return post_created_on.toString();
    }

    public void modifyReacts(boolean check_if_liked) {
        if (check_if_liked)
            reacts_counter++;
        else
            reacts_counter--;
    }
}
