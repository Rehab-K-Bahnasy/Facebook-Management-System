package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PostController {
    @FXML
    Label created;
    @FXML
    Label priv;
    @FXML
    Label caption;
    Stage stage;
    Parent root;
    Scene comment_scene;
    private boolean check_if_liked=false;

    @FXML
    Label like_counter=new Label("0");
    public void displayprivacy(Post post)
    {

        priv.setText(post.post_privacy);
    }
    public void displaycaption(Post post)
    {
        caption.setText(post.post_caption);
    }
    public void displaycounter(Post post)
    {
        like_counter.setText(Integer.toString(counter));
    }
    /*public void switchtofeedscene(ActionEvent event) throws IOException {
       Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("feed.fxml")));
       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       feed_scene=new Scene(root);
       stage.setScene(feed_scene);
       stage.show();
    }*/
    @FXML
    public void switchtocommentscene(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader(Objects.requireNonNull(getClass().getResource("comment.fxml")));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        comment_scene=new Scene(root);
        stage.setScene(comment_scene);
        stage.show();
    }
    public void postliked(ActionEvent event) throws IOException {
        check_if_liked=!check_if_liked;
        modifyreacts(check_if_liked);
        like_counter.setText(Integer.toString(counter));
    }

}