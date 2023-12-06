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
    private PasswordField password_field;
    @FXML
    private PasswordField confirm_password_field;
    @FXML
    private TextField password_text;
    @FXML
    private TextField confirm_password_text;
    @FXML
    private CheckBox show_password;

    public void passwordVisibility() {
        if (show_password.isSelected()) {
            password_text.setText(password_field.getText());
            confirm_password_text.setText(confirm_password_field.getText());
            password_field.setVisible(false);
            confirm_password_field.setVisible(false);
            password_text.setVisible(true);
            confirm_password_text.setVisible(true);
            return;
        }

        password_field.setText(password_text.getText());
        confirm_password_field.setText(confirm_password_text.getText());
        password_text.setVisible(false);
        confirm_password_text.setVisible(false);
        password_field.setVisible(true);
        confirm_password_field.setVisible(true);

    }
    public void toLogin (ActionEvent event) throws IOException {
        WelcomeLogin.switchToScene(event,"WelcomeScene.fxml");
    }
}