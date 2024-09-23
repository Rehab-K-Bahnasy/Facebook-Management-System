package userDashaboard;

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
        username.setText(user.getUsername());
        var curr_user = DataManager.getCurrentUser();
        if (!curr_user.isFriendWith(username.getText())) {
            add_button.setText("Add friend");
        } else if (curr_user.hasMatchingIdentity(username.getText())) {
            add_button.setVisible(false);
        }
    }

    @FXML
    private void addFriend() {

    }

    public static void switchToProfile(ActionEvent event, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileController.class.getResource("ProfileScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        if (user != null) {
            ProfileController profile = fxmlLoader.getController();
            profile.setProfile(user);
        }
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
