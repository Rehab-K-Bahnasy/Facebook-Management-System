package userDashaboard;

import Post.PostController;
import Post.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import welcomeLogin.WelcomeLogin;
import java.io.IOException;

public class HomePageController  {

    static VBox postscontainer;

    @FXML
    private void settings(ActionEvent event) throws IOException {
        SettingsController.switchToSettings(event);
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
