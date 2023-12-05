package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    private Parent root;
    private Scene scene;
    public void start(Stage stage) throws IOException {
        FXMLLoader loader =  new FXMLLoader(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        root=loader.load();
        Post post=loader.getController();
        post.setPost_caption("EZZZZZZZutfiyvhZZZZZZZZZZZZZZZZZZZ");
        post.setPost_privacy("Public");
        post.displaycaption(post);
        post.displayprivacy(post);
        post.displaycounter(post);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}