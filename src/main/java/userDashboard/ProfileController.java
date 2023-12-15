package userDashboard;



import Post.*;
import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Manages the user profile interface, displaying information and handling interactions.
 * Implements Initializable for FXML initialization.
 *
 * @version 1.0
 * @author SOC_IO
 */
public class ProfileController implements Initializable {

    @FXML
    private VBox posts_container;
    @FXML
    private Label name;
    @FXML
    private Label username;
    @FXML
    private Button add_button;
    @FXML
    private CheckBox restrict_button;

    private static String last_scene = "";
    private static User user = DataManager.getCurrentUser();

    /**
     * Initializes the user profile by loading posts and setting up the user interface components.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Post> posts = new ArrayList<>(user.getPosts());
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
        setProfile(user);
    }

    /**
     * Sets the current user profile.
     *
     * @param _user The user whose profile is to be displayed.
     */
    public static void setUser(User _user) {
        user = _user;
    }

    /**
     * Sets the last scene to be used for navigation.
     *
     * @param _last_scene The last scene name.
     */
    public static void setLastScene(String _last_scene) {
        last_scene = _last_scene;
    }

    /**
     * Sets up the user profile with the provided user information.
     *
     * @param user The user whose profile is to be displayed.
     */
    public void setProfile(User user) {
        if (user == null) {
            return;
        }
        name.setText(user.getName());
        username.setText("@" + user.getUsername());
        var curr_user = DataManager.getCurrentUser();
        if (curr_user.hasMatchingIdentity(user.getUsername())) {
            add_button.setVisible(false);
            restrict_button.setVisible(false);
        } else if (!curr_user.isFriendWith(user.getUsername())) {
            add_button.setText("Add friend");
            restrict_button.setVisible(false);
        } else {
            add_button.setText("Friends");
            restrict_button.setVisible(true);
        }
    }

    /**
     * Handles the action of adding a friend.
     * Updates the button text and adds the user to the current user's friend list.
     */
    @FXML
    private void addFriend() {
        add_button.setText("Friends");
        DataManager.getCurrentUser().addFriend(user);
    }

    /**
     * Handles the action of restricting access.
     * Placeholder for future functionality related to restricting user access.
     */
    @FXML
    private void restrict() {
        if (!restrict_button.isVisible()) {
            return;
        }
        // TODO: Implement restriction functionality.
    }

    /**
     * Switches to the user profile scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @param user  The user whose profile is to be displayed.
     * @throws IOException If an I/O error occurs.
     */
    public static void switchToProfile(ActionEvent event, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileController.class.getResource("ProfileScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the action of navigating back to the previous scene.
     *
     * @param event The ActionEvent triggering the navigation.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void back(ActionEvent event) throws IOException {
        if (last_scene.equals("Home")) {
            HomePageController.switchToHomePage(event);
            return;
        }
        SearchController.switchToSearch(event);
    }
}