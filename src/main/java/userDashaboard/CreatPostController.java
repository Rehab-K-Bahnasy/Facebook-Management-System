package userDashaboard;

import Post.Post;
import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatPostController {
    @FXML
    private TextField content;
    @FXML
    private RadioButton public_button;
    @FXML
    private RadioButton private_button;
    @FXML
    private void createPost() {
        String privacy;
        if (public_button.isSelected()) {
            privacy = "public";
        } else if (private_button.isSelected()) {
            privacy = "privacy";
        } else {
            return;
        }
        var user = DataManager.getCurrentUser();
        Post post = new Post(user, content.getText(), privacy);
        user.createPost(post);
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
        HomePageController.switchToHomePage(event);
    }
    public static void switchToHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreatPostController.class.getResource("CreatePostScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}