package welcomeLogin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeLoginController{

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    public void revealPassword() {

    }

    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

    }

    public void createNewAccount() {
        
    }
}