package welcomeLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.EventObject;

public class CreateAccountController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordText;
    @FXML
    private CheckBox showPassword;
    public void toLogin (ActionEvent event) throws IOException {
        WelcomeLogin.switchToScene(event,"WelcomeScene.fxml");
    }
}