package userDashaboard;

import Friend.Friend;
import Post.Post;
import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
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
    private ListView<String> list_view;
    String privacy;
    Post post;
    @FXML
    private void createPost() {

        if (public_button.isSelected()) {
            privacy = "public";
        } else if (private_button.isSelected()) {
            privacy = "privacy";
        } else {
            return;
        }
        var user = DataManager.getCurrentUser();
        post = new Post(user, content.getText(), privacy);
        user.createPost(post);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Post sent");
        alert.setHeaderText("Your post has been uploaded");
        alert.showAndWait();
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
        CreatPostController tagging =fxmlLoader.getController();
        tagging.putFriends();
        stage.show();
    }
@FXML
    public void putFriends(){
        for(var friend : DataManager.getCurrentUser().getAllFriends()){
            list_view.getItems().add(friend.getUsername());
        }
    }
    @FXML
    public void tag(ActionEvent event) throws IOException {
        var selectedItem = list_view.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            DataManager.retrieveUser(selectedItem).getPosts().add(post);
            DataManager.retrieveUser(selectedItem).createPost(post);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tag sent");
            alert.setHeaderText("Your Friend is tagged successfully");
            alert.showAndWait();
        }
    }




}