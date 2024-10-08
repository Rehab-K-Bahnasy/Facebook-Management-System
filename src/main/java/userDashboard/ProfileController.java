package userDashboard;


import Friend.Friend;
import Post.*;
import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages the user profile interface, displaying information and handling interactions.
 * Implements Initializable for FXML initialization.
 *
 * @author SOC_IO
 * @version 1.0
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
    @FXML
    private Button friendship_button;
    @FXML
    private Button mutual_button;
    @FXML
    private ListView<String> mutual_list;
    @FXML
    private Button close_list;
    @FXML
    private Label friendship_date;
    @FXML
    private ScrollPane common_pane;
    @FXML
    private VBox common_posts;
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
                if (!user.getUsername().equals(post.getCreatorUsername()) && post.getPrivacy().equals("private")) {
                    var creator = DataManager.retrieveUser(post.getCreatorUsername());
                    var friend = creator.getFriend(user.getUsername());
                    if (friend != null) {
                        var type = friend.getFriendshipType();
                        if (type.equals("RESTRICTED")) {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
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
        setProfile();
        setMutual();
        setFriendship();
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
     */
    public void setProfile() {
        if (user == null) {
            return;
        }
        name.setText(user.getName());
        username.setText("@" + user.getUsername());
        var curr_user = DataManager.getCurrentUser();
        if (curr_user.hasMatchingIdentity(user.getUsername())) {
            add_button.setVisible(false);
            restrict_button.setVisible(false);
            mutual_button.setText("My Friends");
            friendship_button.setVisible(false);
        } else if (!curr_user.isFriendWith(user.getUsername())) {
            add_button.setText("Add friend");
            restrict_button.setVisible(false);
            mutual_button.setVisible(false);
            friendship_button.setVisible(false);
        } else {
            add_button.setText("You're friends");
            restrict_button.setVisible(true);
            var friend = curr_user.getFriend(user.getUsername());
            var type = curr_user.getFriendType(friend);
            restrict_button.setSelected(type.equals("RESTRICTED"));
        }
    }

    /**
     * Handles the action of adding a friend.
     * Updates the button text and adds the user to the current user's friend list.
     */
    @FXML
    private void addFriend() {
        var curr_user = DataManager.getCurrentUser();
        if (curr_user.getFriend(user.getUsername()) != null) {
            return;
        }
        add_button.setText("Friends");
        curr_user.addFriend(user);
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
        var curr_user = DataManager.getCurrentUser();
        var friend = curr_user.getFriend(user.getUsername());
        if (restrict_button.isSelected()) {
            curr_user.restrictFriend(friend);
        } else {
            curr_user.unrestrictFriend(friend);
        }
    }

    private void setMutual() {
        var curr_user = DataManager.getCurrentUser();
        ArrayList<Friend> mutual = new ArrayList<>(curr_user.getAllFriends());
        mutual.retainAll(user.getAllFriends());
        for (var friend : mutual) {
            mutual_list.getItems().add(friend.getName());
        }
    }

    @FXML
    private void seeMutual() {
        close_list.setVisible(true);
        mutual_list.setVisible(true);
    }

    @FXML
    private void closeList() {
        close_list.setVisible(false);
        mutual_list.setVisible(false);
        common_pane.setVisible(false);
        common_pane.setVisible(false);
        friendship_date.setVisible(false);
    }

    private void setFriendship() {
        var curr_user = DataManager.getCurrentUser();
        var friend = curr_user.getFriend(user.getUsername());
        if(friend == null) {
            return;
        }
        var date = curr_user.getFriendshipDate(friend).toString();
        friendship_date.setText("Friends since: " + date);
        friendship_date.setVisible(true);
        var curr_user_posts = curr_user.getPosts();
        var user_posts = user.getPosts();
        var common = new ArrayList<>(List.copyOf(curr_user_posts));
        common.retainAll(user_posts);
        for (var post : common) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Post.class.getResource("PostScene.fxml"));
                VBox vbox = fxmlLoader.load();
                PostController postController = fxmlLoader.getController();
                postController.setData(post);
                common_posts.getChildren().add(vbox);
            } catch (IOException e) {
                System.out.println("IOException caught");
            }
        }
    }

    @FXML
    private void seeFriendship() {
        common_posts.setVisible(true);
        common_pane.setVisible(true);
        close_list.setVisible(true);
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