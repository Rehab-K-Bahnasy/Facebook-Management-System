package userDashaboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;

public class ProfileController {
    @FXML
    Label name;
    @FXML
    Label username;
    public void setProfile(User user) {
        if(user==null)
            return;
        name.setText(user.getName());
        username.setText(user.getUsername());
    }
    public static void switchToProfile(ActionEvent event,User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileController.class.getResource("ProfileScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ProfileController profile = fxmlLoader.getController();
        profile.setProfile(user);
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        SearchController.switchToSearch(event);
    }
}
