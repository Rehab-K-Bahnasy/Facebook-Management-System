package welcomeLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import user.User;
import validator.Validator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.EventObject;

public class CreateAccountController {
    @FXML
    private static TextField first_name;
    @FXML
    private static TextField last_name;
    @FXML
    private static TextField username;
    @FXML
    private static TextField email;
    @FXML
    private static TextField phone_number;
    @FXML
    private static DatePicker birth_date;
    @FXML
    private static RadioButton male;
    @FXML
    private static RadioButton female;
    @FXML
    private static PasswordField password_field;
    @FXML
    private static PasswordField confirm_password_field;
    @FXML
    private static TextField password_text;
    @FXML
    private static TextField confirm_password_text;
    @FXML
    private static CheckBox show_password;
    @FXML
    private static Button sign_up;
    @FXML
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

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
    public static String getFirstName(){
        return first_name.getText();
    }
    public static String getLastName(){
        return last_name.getText();
    }
    public static String getEmail(){
        return email.getText();
    }
    public static String getUsername(){
        return username.getText();
    }
    public static String getPhoneNumber(){
        return phone_number.getText();
    }
    public static LocalDate getBirthdate(){
        return birth_date.getValue();
    }
    public static String getPassword(){
        return password_field.getText();
    }
    public static String getConfirmPassword(){
        return confirm_password_field.getText();
    }
    public void signUp() {
        if (Validator.checkUsernameValidation() && !(Validator.checkPhoneNumberValidation())
                && Validator.checkPasswordVerification() && Validator.checkEmailValidation() && Validator.checkAdult()) {
            alert.setTitle("Welcome back ya m3lm");
            alert.setHeaderText("Welcome back ya m3lm");
            alert.showAndWait();
        }
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong login credentials");
        alert.setHeaderText("We couldn't find an account with that username.\n" +
                "Please check your credentials and try again.");
        alert.showAndWait();
    }
    public void toLogin (ActionEvent event) throws IOException {
        WelcomeLogin.switchToScene(event,"WelcomeScene.fxml");
    }

}