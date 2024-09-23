package userDashboard;

import Friend.Friend;
import Post.Post;
import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Manages the creation of user posts, allowing users to compose and submit new posts.
 * Handles user interactions related to post creation and navigation.
 *
 * @author SOC-IO
 * @version 1.0
 */

public class CreatPostController implements Initializable {
    @FXML
    private TextField content;
    @FXML
    private RadioButton public_button;
    @FXML
    private RadioButton private_button;
    @FXML
    private ListView<NameUsername> friends_list;
    @FXML
    private ListView<NameUsername> tagging_list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMutual();
    }

    private void setMutual() {
        var curr_user = DataManager.getCurrentUser();
        ArrayList<Friend> friends = new ArrayList<>(curr_user.getAllFriends());
        for (var friend : friends) {
            var friendLabel = new NameUsername(friend.getName(), friend.getUsername());
            friends_list.getItems().add(friendLabel);
        }
    }

    /**
     * Creates a new post based on user input and adds it to the user's posts.
     * Displays an information alert upon successful post creation.
     */
    @FXML
    private void createPost(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String privacy;
        if (public_button.isSelected()) {
            privacy = "public";
        } else if (private_button.isSelected()) {
            privacy = "private";
        } else {
            alert.setTitle("Undefined privacy");
            alert.setHeaderText("Must determine the privacy");
            alert.showAndWait();
            return;
        }
        if (content.getText().isEmpty()) {
            alert.setTitle("Empty post");
            alert.setHeaderText("Cannot reply with an empty comment");
            alert.showAndWait();
            return;
        }
        var user = DataManager.getCurrentUser();
        ArrayList<User> tags = new ArrayList<>();
        for (var item : tagging_list.getItems()) {
            tags.add(DataManager.retrieveUser(item.username));
        }
        Post post;
        if (tags.isEmpty()) {
            post = new Post(content.getText(), privacy);
        } else {
            post = new Post(content.getText(), privacy, tags);
        }
        user.createPost(post);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Post sent");
        alert.setHeaderText("Your post has been uploaded");
        alert.showAndWait();
        switchToCreatePost(event);
    }

    @FXML
    private void addToTags() {
        var selected = friends_list.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        for (var item : tagging_list.getItems()) {
            if (selected == item) {
                return;
            }
        }
        tagging_list.getItems().add(selected);
    }

    @FXML
    private void removeFromTags() {
        var selected = tagging_list.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        tagging_list.getItems().remove(selected);
    }

    /**
     * Navigates back to the home page.
     *
     * @param event The ActionEvent triggering the navigation.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void back(ActionEvent event) throws IOException {
        HomePageController.switchToHomePage(event);
    }

    /**
     * Switches to the creation post scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
    public static void switchToCreatePost(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreatPostController.class.getResource("CreatePostScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
