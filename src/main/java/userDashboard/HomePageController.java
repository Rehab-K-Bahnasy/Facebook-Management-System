
package userDashboard;

import Post.Post;
import Post.PostController;
import conversation.MessageController;
import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import welcomeLogin.WelcomeLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Manages the user's home page interface, displaying posts and handling user interactions.
 * Implements Initializable for FXML initialization.
 *
 * @author SOC-IO
 * @version 1.0
 */

public class HomePageController implements Initializable {

    @FXML
    private VBox posts_container;

    /**
     * Initializes the user's home page by loading posts and setting up the interface components.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Post> posts = new ArrayList<>(DataManager.getCurrentUser().getFeed());
        for (var post : posts) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Post.class.getResource("PostScene.fxml"));
                VBox vbox = fxmlLoader.load();
                PostController postController = fxmlLoader.getController();
                postController.setData(post);
                posts_container.getChildren().add(vbox);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Switches to the settings scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void settings(ActionEvent event) throws IOException {
        SettingsController.switchToSettings(event);
    }

    /**
     * Switches to the messages scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void message(ActionEvent event) throws IOException {
        MessageController.switchToMessages(event);
    }

    /**
     * Switches to the search scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void search(ActionEvent event) throws IOException {
        SearchController.switchToSearch(event);
    }

    /**
     * Switches to the user profile scene.
     *
     * @param actionEvent The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void profile(ActionEvent actionEvent) throws IOException {
        ProfileController.setUser(DataManager.getCurrentUser());
        ProfileController.setLastScene("Home");
        ProfileController.switchToProfile(actionEvent, DataManager.getCurrentUser());
    }

    /**
     * Switches to the create post scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void createPost(ActionEvent event) throws IOException {
        CreatPostController.switchToHomePage(event);
    }

    /**
     * Logs out the user, displaying a confirmation dialog.
     *
     * @param event The ActionEvent triggering the logout.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("log out");
        alert.setHeaderText("You're about to leave :(");
        alert.setContentText("Are you sure you want to logout?");
        var result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                WelcomeLogin.switchToScene(event, "WelcomeScene.fxml");
            }
        }
    }

    /**
     * Switches to the home page scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
    public static void switchToHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("HomePageScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
