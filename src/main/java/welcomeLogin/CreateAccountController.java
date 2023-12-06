package welcomeLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordText;
    @FXML
    private CheckBox showPassword;
    public void createNewAccount(ActionEvent event) throws IOException {
        WelcomeLogin.fxmlLoader = new FXMLLoader(WelcomeLogin.class.getResource("WelcomeScene.fxml"));
        WelcomeLogin.root = WelcomeLogin.fxmlLoader.load();
        WelcomeLogin.mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        WelcomeLogin.scene = new Scene(WelcomeLogin.root);
        WelcomeLogin.mainStage.setScene(WelcomeLogin.scene);
        WelcomeLogin.mainStage.show();
    }
    public void passwordVisibility() {
        if (showPassword.isSelected()) {
            passwordText.setText(passwordField.getText());
            passwordText.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordText.getText());
        passwordField.setVisible(true);
        passwordText.setVisible(false);
    }
}