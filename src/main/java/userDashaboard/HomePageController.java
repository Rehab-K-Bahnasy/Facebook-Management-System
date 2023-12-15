package userDashaboard;

import Post.Post;
import Post.PostController;
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
import user.Message;
import welcomeLogin.WelcomeLogin;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private VBox posts_container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Post> posts = new ArrayList<>(getPosts());
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

    public List<Post> getPosts() {

        List<Post> list_post = new ArrayList<>();
        Post post;
        for (int i = 0; i < 4; i++) {
            post = new Post(312, "hello world", LocalDate.now(), "public");
            post.setUsername("@mobakry");
            post.setName("mohamed");
            list_post.add(post);
        }

        return list_post;
    }

    @FXML
    private void settings(ActionEvent event) throws IOException {
        SettingsController.switchToSettings(event);
    }
    @FXML
    private void search(ActionEvent event) throws IOException {
        SearchController.switchToSearch(event);
    }
    @FXML
    private void message(ActionEvent event) throws IOException {
        MessagingController.switchToMessage(event);
    }
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

    public static void switchToHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("HomePageScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
