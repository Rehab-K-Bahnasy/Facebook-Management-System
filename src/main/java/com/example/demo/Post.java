package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class Post {

    @FXML
    Label created;
    @FXML
    Label priv;
    @FXML
    Label caption;
    Stage stage;
    Parent root;
    Scene feed_scene;
    Scene comment_scene;
    private int post_ID;
    private LocalDate post_created_on = LocalDate.now();
    private String  post_caption;
    private int post_reacts;
    //private User [] users_tagged_in_post = new User[10000];
    private ArrayList<Comment> comments_on_post = new ArrayList<Comment>();
    private String post_privacy;

    public void setPost_reacts(int post_reacts) {
        this.post_reacts = post_reacts;
    }

    public void setComments_on_post(ArrayList<Comment> comments_on_post) {
        this.comments_on_post = comments_on_post;
    }

    public void setPost_caption(String post_caption) {
        this.post_caption = post_caption;
    }

    public void setPost_ID(int post_ID) {
        this.post_ID = post_ID;
    }

    public void setPost_privacy(String post_privacy) {
        this.post_privacy = post_privacy;
    }


    public int getPost_reacts() {
        return post_reacts;
    }

    public ArrayList<Comment> getComments_on_post() {
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
    /*public void switchtofeedscene(ActionEvent event) throws IOException {
       Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("feed")));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       feed_scene=new Scene(root);
       stage.setScene(feed_scene);
       stage.show();
    }*/
    public void switchtocommentscene(ActionEvent event) throws IOException {
       Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("feed")));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       comment_scene=new Scene(root);
       stage.setScene(feed_scene);
       stage.show();
    }
}
