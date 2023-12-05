package welcomeLogin;

import dataManager.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class WelcomeLoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordText;
    @FXML
    private CheckBox showPassword;
    public void passwordVisibility() {
        if(showPassword.isSelected())
        {
            passwordText.setText(passwordField.getText());
            passwordText.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordText.getText());
        passwordField.setVisible(true);
        passwordText.setVisible(false);
    }

    public void login() {
        String inputEmail = emailField.getText();
        String inputPassword = passwordField.getText();
        if(showPassword.isSelected())
            inputPassword = passwordText.getText();
        Alert alert;
//        if(!DataManager.isValidUserCredentials(inputEmail,inputPassword))
//        {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Wrong login credentials");
//            alert.setHeaderText("We couldn't find an account with that username.\n" +
//                    "Please check your credentials and try again.");
//            alert.showAndWait();
//            return;
//        }
//        alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Welcome back ya m3lm");
//        alert.setHeaderText("Welcome back ya m3lm");
//        alert.showAndWait();
//        //else: switch scene to the user home page
    }

    public void createNewAccount() {

    }
}