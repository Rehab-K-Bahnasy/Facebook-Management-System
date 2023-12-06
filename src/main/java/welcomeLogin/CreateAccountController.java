package welcomeLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountController {
    public void createNewAccount(ActionEvent event) throws IOException {
        WelcomeLogin.fxmlLoader = new FXMLLoader(WelcomeLogin.class.getResource("WelcomeScene.fxml"));
        WelcomeLogin.root = WelcomeLogin.fxmlLoader.load();
        WelcomeLogin.mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        WelcomeLogin.scene = new Scene(WelcomeLogin.root);
        WelcomeLogin.mainStage.setScene(WelcomeLogin.scene);
        WelcomeLogin.mainStage.show();
    }
}