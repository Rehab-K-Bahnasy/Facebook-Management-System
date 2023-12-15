package userDashboard;

import Post.Post;
import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Manages the creation of user posts, allowing users to compose and submit new posts.
 * Handles user interactions related to post creation and navigation.
 *
 * @author SOC-IO
 * @version 1.0
 */

public class CreatPostController {

    @FXML
    private TextField content;
    @FXML
    private RadioButton public_button;
    @FXML
    private RadioButton private_button;

    /**
     * Creates a new post based on user input and adds it to the user's posts.
     * Displays an information alert upon successful post creation.
     */
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
        Post post = new Post(content.getText(), privacy);
        user.createPost(post);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Post sent");
        alert.setHeaderText("Your post has been uploaded");
        alert.showAndWait();
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
     * Switches to the create post scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
    public static void switchToHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreatPostController.class.getResource("CreatePostScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
