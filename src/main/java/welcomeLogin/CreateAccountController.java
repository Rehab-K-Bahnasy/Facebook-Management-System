package welcomeLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import validator.Validator;

import java.io.IOException;
import java.time.LocalDate;


public class CreateAccountController {
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField phone_number;
    @FXML
    private DatePicker birth_date;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
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
    @FXML
    private Button sign_up;
    @FXML
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private String gender_check = null;
    private String field_style = null;
    private String error_message;
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

    public void setGenderGroup() {
        if (gender_check == null) {
            if (male.isSelected()) {
                gender_check = "M";
            }
            if (female.isSelected()) {
                gender_check = "F";
            }
        }
        if (male.isSelected() && gender_check.equals("F")) {
            male.setSelected(true);
            gender_check = "M";
            female.setSelected(false);
        } else if (female.isSelected() && gender_check.equals("M")) {
            female.setSelected(true);
            gender_check = "F";
            male.setSelected(false);
        }

    }

    public String getFirstName() {
        return first_name.getText();
    }

    public String getLastName() {
        return last_name.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPhoneNumber() {
        return phone_number.getText();
    }

    public LocalDate getBirthdate() {
        return birth_date.getValue();
    }

    public String getPassword() {
        return password_field.getText();
    }

    public String getConfirmPassword() {
        return confirm_password_field.getText();
    }

    public boolean getMale() {
        return male.isSelected();
    }

    public boolean getFemale() {
        return female.isSelected();
    }

    public void signUp () {
        if (field_style == null) {
            field_style = email.getStyle();
        }
        error_message = "";
        // must be operated on more than one line to avoid short cycle
        boolean canSignUp = manageNameFields(first_name);
        canSignUp &= manageNameFields(last_name);
        canSignUp &= manageUsernameField();
        canSignUp &= manageEmailField();
        canSignUp &= managePhoneNumberField();
        canSignUp &= managePasswordField();
        canSignUp &= manageDateField();
        canSignUp &= (getFemale() | getMale());
        System.out.println(canSignUp);

        if (canSignUp) {
            alert.setTitle("HELLOZ");
            alert.setHeaderText("Welcome to SOC-IO <3");
            alert.showAndWait();
        } else {
            alert.setTitle("invalid credentials");
            alert.setHeaderText(error_message);
            alert.showAndWait();
            error_message = "";
        }
    }

    private boolean manageNameFields(TextField field) {

        if ((field.getText() != null) && !Validator.checkNameValidation(field.getText())) {
            if (!error_message.startsWith("Name")) {
                error_message += "Name can't contain numbers\n";
            }
            field.setStyle("-fx-border-color: #9a0e0e");
            return false;
        }
        field.setStyle(field_style);
        return true;
    }

    private boolean manageUsernameField() {
        if (!Validator.checkUniqueUsername(username.getText())) {
            error_message += "Username must be unique\n";
            username.setStyle("-fx-border-color: #9a0e0e");
            return false;
        }
        username.setStyle(field_style);
        return true;
    }

    private boolean manageEmailField() {
        int error = Validator.checkEmail(email.getText());
        if (error > 0) {
            if (error == 1)
                error_message += "Email should follow the given format\n";
            else
                error_message += "Email must be unique\n";

            email.setStyle("-fx-border-color: #9a0e0e");
            return false;
        }
        email.setStyle(field_style);
        return true;
    }

    private boolean managePhoneNumberField() {
        int error = Validator.checkPhone(phone_number.getText());
        if (error > 0) {
            if(error == 1)
                error_message += "Phone number should follow the format\n";
            else
                error_message += "Phone number is used before\n";

            phone_number.setStyle("-fx-border-color: #9a0e0e");
            return false;
        }
        phone_number.setStyle(field_style);
        return true;
    }

    private boolean managePasswordField() {
        //check password format
        if (!Validator.checkPasswordVerification(password_field.getText(), confirm_password_field.getText())){
            error_message += "Password doesn't match\n";
            password_field.setStyle("-fx-border-color: #9a0e0e");
            confirm_password_field.setStyle("-fx-border-color: #9a0e0e");
            return false;
        }
        password_field.setStyle(field_style);
        confirm_password_field.setStyle(field_style);
        return true;
    }

    private boolean manageDateField() {
        if ((birth_date.getValue() == null) || !Validator.checkAdult(birth_date.getValue())) {
            error_message += "You must be more than 13 to join SOC-IO";
            birth_date.setStyle("-fx-border-color: #9a0e0e");
            return false;
        }
        birth_date.setStyle(field_style);
        return true;
    }

    public void toLogin(ActionEvent event) throws IOException {
        WelcomeLogin.switchToScene(event, "WelcomeScene.fxml");
    }

}