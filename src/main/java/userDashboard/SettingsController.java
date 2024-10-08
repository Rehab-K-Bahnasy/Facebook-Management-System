package userDashboard;

import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import user.User;
import validator.Validator;

import java.io.IOException;

/**
 * Controller class for the user settings view.
 * Handles user input and updates user settings.
 *
 *  @version 1.0
 *  @author SOC-IO
 */
public class SettingsController {

    @FXML
    TextField first_name;
    @FXML
    TextField last_name;
    @FXML
    TextField phone_number;
    @FXML
    DatePicker birthdate;
    @FXML
    TextField email;
    @FXML
    TextField username;
    @FXML
    PasswordField old_password;
    @FXML
    PasswordField new_password;

    String error_message;
    String field_style;

    /**
     * Cancels any changes made and switches back to the home page.
     *
     * @param event The event triggered by the cancel button.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    private void cancelChanges(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel changes");
        alert.setHeaderText("Any changes made won't be saved");
        alert.setContentText("Are you sure you want to cancel?");
        var result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                HomePageController.switchToHomePage(event);
            }
        }
    }

    /**
     * Saves the changes made by the user in the settings.
     *
     * @param event The event triggered by the save button.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    private void saveChanges(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save changes");
        alert.setHeaderText("Any changes made will be saved");
        alert.setContentText("Are you sure you want to save?");
        var result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                boolean check_valid_changes = changeUserSettings();
                if (check_valid_changes) {
                    if (!email.getText().isEmpty())
                        DataManager.getCurrentUser().setEmail(email.getText());
                    if (!username.getText().isEmpty())
                        DataManager.getCurrentUser().setUsername(username.getText());
                    if (!first_name.getText().isEmpty())
                        DataManager.getCurrentUser().setFirstName(first_name.getText());
                    if (!last_name.getText().isEmpty())
                        DataManager.getCurrentUser().setLastName(last_name.getText());
                    if (birthdate.getValue() != null)
                        DataManager.getCurrentUser().setBirthdate(birthdate.getValue().toString());
                    if (!phone_number.getText().isEmpty())
                        DataManager.getCurrentUser().setPhoneNumber(phone_number.getText());

                    HomePageController.switchToHomePage(event);
                }
            }
        }
    }

    /**
     * Switches to the settings view.
     *
     * @param event The event triggered to switch to the settings view.
     * @throws IOException if an I/O error occurs.
     */
    public static void switchToSettings(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource("SettingsScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        SettingsController settings = fxmlLoader.getController();
        settings.setSettingsDetails(DataManager.getCurrentUser());
    }

    /**
     * Sets the initial details for the user settings.
     *
     * @param current_user The user whose settings are displayed.
     */
    public void setSettingsDetails(User current_user) {
        first_name.setPromptText(current_user.getFirstName());
        last_name.setPromptText(current_user.getLastName());
        email.setPromptText(current_user.getEmail());
        phone_number.setPromptText(current_user.getPhoneNumber());
        username.setPromptText(current_user.getUsername());
        birthdate.setPromptText(current_user.getBirthdate().toString());
    }

    /**
     * Validates and updates the user settings based on user input.
     *
     * @return true if the user settings are valid and updated, false otherwise.
     */
    public boolean changeUserSettings() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (field_style == null) {
            field_style = email.getStyle();
        }
        error_message = "";
        boolean can_change_settings = manageNameFields(first_name);
        can_change_settings &= manageNameFields(last_name);
        can_change_settings &= manageUsernameField();
        can_change_settings &= manageEmailField();
        can_change_settings &= managePhoneNumberField();
        can_change_settings &= managePasswordField();
        can_change_settings &= manageDateField();

        if (!can_change_settings) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("invalid credentials");
            alert.setHeaderText(error_message);
            alert.showAndWait();
            error_message = "";
            return false;
        }
        return true;
    }

    /**
     * Validates the name fields.
     *
     * @param field The TextField to be validated.
     * @return true if the name is valid, false otherwise.
     */
    private boolean manageNameFields(TextField field) {
        if (!field.getText().isEmpty()) {
            if (!Validator.checkNameValidation(field.getText())) {
                if (!error_message.startsWith("Name")) {
                    error_message += "Name can't contain numbers\n";
                }
                field.setStyle("-fx-border-color: #9a0e0e");
                return false;
            }
        }
        field.setStyle(field_style);
        return true;
    }

    /**
     * Validates the username field.
     *
     * @return true if the username is valid, false otherwise.
     */
    private boolean manageUsernameField() {
        if (!username.getText().isEmpty()) {
            if (!Validator.checkUniqueUsername(username.getText())) {
                error_message += "Username must be unique\n";
                username.setStyle("-fx-border-color: #9a0e0e");
                return false;
            }
        }
        username.setStyle(field_style);
        return true;
    }

    /**
     * Validates the email field.
     *
     * @return true if the email is valid, false otherwise.
     */
    private boolean manageEmailField() {
        if (!email.getText().isEmpty()) {
            int error = Validator.checkEmail(email.getText());
            if (error > 0) {
                if (error == 1)
                    error_message += "Email should follow the given format\n";
                else
                    error_message += "Email must be unique\n";

                email.setStyle("-fx-border-color: #9a0e0e");
                return false;
            }
        }
        email.setStyle(field_style);
        return true;
    }

    /**
     * Validates the phone number field.
     *
     * @return true if the phone number is valid, false otherwise.
     */
    private boolean managePhoneNumberField() {
        if (!phone_number.getText().isEmpty()) {
            int error = Validator.checkPhone(phone_number.getText());
            if (error > 0) {
                if (error == 1)
                    error_message += "Phone number should follow the format\n";
                else
                    error_message += "Phone number is used before\n";

                phone_number.setStyle("-fx-border-color: #9a0e0e");
                return false;
            }
        }
        phone_number.setStyle(field_style);
        return true;
    }

    /**
     * Validates the password fields.
     *
     * @return true if the password is valid, false otherwise.
     */
    private boolean managePasswordField() {
        if (!old_password.getText().isEmpty()) {
            String password = old_password.getText();
            String new_password_string = new_password.getText();
            if (!DataManager.getCurrentUser().hasPasswordMatch(password)) {
                error_message += "Password isn't correct\n";
                old_password.setStyle("-fx-border-color: #9a0e0e");
                return false;
            }
            old_password.setStyle(field_style);
            DataManager.getCurrentUser().setPassword(new_password_string);
        }
        return true;
    }

    /**
     * Validates the birthdate field.
     *
     * @return true if the birthdate is valid, false otherwise.
     */
    private boolean manageDateField() {
        if (birthdate.getValue() != null) {
            if ((birthdate.getValue() == null) || !Validator.checkAdult(birthdate.getValue())) {
                error_message += "You must be more than 13 to join SOC-IO";
                birthdate.setStyle("-fx-border-color: #9a0e0e");
                return false;
            }
        }
        birthdate.setStyle(field_style);
        return true;
    }

}
