package welcomeLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    public void login() {
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
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Welcome back ya m3lm");
        alert.setHeaderText("Welcome back ya m3lm");
        alert.showAndWait();
        //else: switch scene to the user home page
    }

    public void createNewAccount(ActionEvent event) throws IOException {
        WelcomeLogin.switchToScene(event, "CreateAccountScene.fxml");
    }
}