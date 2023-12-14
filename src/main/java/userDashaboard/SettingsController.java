package userDashaboard;

import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;

public class SettingsController {
    @FXML
    TextField first_name;
    @FXML
    TextField last_name;
    @FXML
    TextField phone_number;
    @FXML
    DatePicker birthdate;
    @FXML
    TextField email;
    @FXML
    TextField username;
    @FXML
    PasswordField old_password;
    @FXML
    PasswordField new_password;
    @FXML
    private void cancelChanges(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel changes");
        alert.setHeaderText("Any changes made won't be saved");
        alert.setContentText("Are you sure you want to cancel?");
        var result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                HomePageController.switchToHomePage(event);
            }
        }
    }
    @FXML
    private void saveChanges(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save changes");
        alert.setHeaderText("Any changes made will be saved");
        alert.setContentText("Are you sure you want to save?");
        var result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                HomePageController.switchToHomePage(event);
            }
        }
    }
    public static void switchToSettings(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource("SettingsScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        SettingsController settings = fxmlLoader.getController();
        settings.setSettingsDetails(DataManager.getCurrentUser());
    }

    public void setSettingsDetails(User current_user){
       first_name.setPromptText(current_user.getFirstName());
       last_name.setPromptText(current_user.getLastName());
       email.setPromptText(current_user.getEmail());
       phone_number.setPromptText(current_user.getPhoneNumber());
       username.setPromptText(current_user.getUsername());
       birthdate.setPromptText(current_user.getBirthdate().toString());
    }
}