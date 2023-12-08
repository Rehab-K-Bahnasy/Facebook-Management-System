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
    private  TextField last_name;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private  TextField phone_number;
    @FXML
    private  DatePicker birth_date;
    @FXML
    private  RadioButton male;
    @FXML
    private  RadioButton female;
    @FXML
    private  PasswordField password_field;
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
    private String gender_check = null;
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
    public void setGenderGroup(){
        if(gender_check == null)
        {
            if(male.isSelected()){
                gender_check = "M";
            }
            if(female.isSelected()){
                gender_check = "F";
            }
        }
        if(male.isSelected() && gender_check.equals("F")){
            male.setSelected(true);
            gender_check = "M";
            female.setSelected(false);
        }
        else if(female.isSelected() && gender_check.equals("M")){
            female.setSelected(true);
            gender_check = "F";
            male.setSelected(false);
        }

    }

    public  String getFirstName(){
        return first_name.getText();
    }
    public String getLastName(){
        return last_name.getText();
    }
    public String getEmail(){
        return email.getText();
    }
    public String getUsername(){
        return username.getText();
    }
    public String getPhoneNumber(){
        return phone_number.getText();
    }
    public LocalDate getBirthdate(){
        return birth_date.getValue();
    }
    public String getPassword(){
        return password_field.getText();
    }
    public String getConfirmPassword(){
        return confirm_password_field.getText();
    }
    public boolean getMale(){
        return male.isSelected();
    }
    public boolean getFemale(){
        return female.isSelected();
    }
    public void signUp() {
         if (/*Validator.checkNameValidation(getFirstName()) &&Validator.checkNameValidation(getLastName())  && (Validator.checkPhoneNumberValidation(getPhoneNumber()))
                && Validator.checkPasswordVerification(getPassword(),getConfirmPassword()) && Validator.checkEmailValidation(getEmail()) && !(Validator.checkAdult(getBirthdate()))*/ Validator.genderCheckValidation(getMale(),getFemale())) {
           System.out.println(username.getText());
        }
         else
        System.out.println("damn u");

    }
    public void toLogin (ActionEvent event) throws IOException {
        WelcomeLogin.switchToScene(event,"WelcomeScene.fxml");
    }

}