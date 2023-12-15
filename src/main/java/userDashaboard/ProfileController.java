package userDashaboard;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Post> posts = new ArrayList<>(user.getPosts());
        for (var post : posts) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Post.class.getResource("PostScene.fxml"));
                VBox vbox = fxmlLoader.load();
                PostController postcontroller = fxmlLoader.getController();
                postcontroller.setData(post);
                posts_container.getChildren().add(vbox);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void setUser(User _user) {
        user = _user;
    }

    public static void setLastScene(String _last_scene) {
        last_scene = _last_scene;
    }

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
        }
        else
        {
            add_button.setText("friends");
            restrict_button.setVisible(true);
        }
    }

    @FXML
    private void addFriend() {
        add_button.setText("friends");
        DataManager.getCurrentUser().addFriend(DataManager.getCurrentUser(),new Friend(DataManager.getCurrentUser().changeUserintoMap(DataManager.retrieveUser(username.getText()))));
    }

    @FXML
    private void restrict() {
        if (!restrict_button.isVisible()) {
            return;
        }

    }

    public static void switchToProfile(ActionEvent event, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileController.class.getResource("ProfileScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        if (user != null) {
            ProfileController profile = fxmlLoader.getController();
            profile.setProfile(user);
        }
        stage.show();
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        if (last_scene.equals("Home")) {
            HomePageController.switchToHomePage(event);
            return;
        }
        SearchController.switchToSearch(event);
    }
}
