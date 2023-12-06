package creatAccount;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class CreateAccountController {

    @FXML
    private RadioButton radioButton1;

    @FXML
    private RadioButton radioButton2;

    // ... Other code ...

    public void initialize() {
        // Create a ToggleGroup to restrict selection
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);

        // Add the radio buttons to a layout pane
        Pane pane = new Pane();
        pane.getChildren().addAll(radioButton1, radioButton2);
    }
}