package welcomeLogin;

import dataManager.DataManager;
import userDashaboard.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import validator.Validator;

import java.io.IOException;

public class WelcomeLoginController {
    @FXML
    private TextField identity_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private TextField password_text;
    @FXML
    private CheckBox show_password;

   private static String CurrentUserIdentity;
    public void passwordVisibility() {
        if (show_password.isSelected()) {
            password_text.setText(password_field.getText());
            password_text.setVisible(true);
            password_field.setVisible(false);
            return;
        }
        password_field.setText(password_text.getText());
        password_field.setVisible(true);
        password_text.setVisible(false);
    }

    public void login(ActionEvent event) throws IOException {
        String input_identity = identity_field.getText();
        String input_password = password_field.getText();
        if (show_password.isSelected()) {
            input_password = password_text.getText();
        }

        Alert alert;
        if (!Validator.checkLoginValidation(input_identity, input_password)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong login credentials");
            alert.setHeaderText("We couldn't find an account with that username.\n" +
                    "Please check your credentials and try again.");
            alert.showAndWait();
            return;
        }
        DataManager.setCurrentUser(DataManager.retrieveUser(input_identity));
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Welcome back ya m3lm");
        alert.setHeaderText("Welcome back ya m3lm");
        var result = alert.showAndWait();
        if(result.isPresent()) {
            if(result.get()== ButtonType.OK) {
                HomePageController.switchToHomePage(event);
            }
        }
    }

    public void createNewAccount(ActionEvent event) throws IOException {
        WelcomeLogin.switchToScene(event, "CreateAccountScene.fxml");
    }
    public static String getCurrentUserIdentity()
    {
        return CurrentUserIdentity;
    }
}