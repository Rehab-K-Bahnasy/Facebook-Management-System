package welcomeLogin;

import dataManager.DataManager;
import userDashboard.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import validator.Validator;

import java.io.IOException;
/**
 * The `WelcomeLoginController` class handles user interactions for the welcome/login scene.
 * It is associated with the "WelcomeScene.fxml" FXML file and provides methods for handling login attempts,
 * creating new accounts, and managing password visibility.
 * Utilizes the `DataManager` for user data management, `HomePageController` for scene switching,
 * `Validator` for input validation, and JavaFX components for the graphical user interface.
 *
 * This controller includes methods for toggling password visibility, validating login credentials,
 * and navigating to the home page or account creation scene based on user interactions.
 *
 * @author SOC-IO
 * @version 1.0
 */

public class WelcomeLoginController {

    @FXML
    private TextField identity_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private TextField password_text;
    @FXML
    private CheckBox show_password;

    private static String CurrentUserIdentity;

    /**
     * Toggles the visibility of the password based on the state of the "Show Password" checkbox.
     * Displays the plain text password or the password field accordingly.
     */
    public void passwordVisibility() {
        if (show_password.isSelected()) {
            password_text.setText(password_field.getText());
            password_text.setVisible(true);
            password_field.setVisible(false);
            return;
        }
        password_field.setText(password_text.getText());
        password_field.setVisible(true);
        password_text.setVisible(false);
    }

    /**
     * Handles the login attempt by validating the user's credentials and navigating to the home page on success.
     * Displays appropriate alerts for successful login or incorrect credentials.
     *
     * @param event The ActionEvent triggering the login attempt.
     * @throws IOException If an I/O error occurs during scene switching.
     */
    public void login(ActionEvent event) throws IOException {
        String input_identity = identity_field.getText();
        String input_password = password_field.getText();
        if (show_password.isSelected()) {
            input_password = password_text.getText();
        }

        Alert alert;
        if (!Validator.checkLoginValidation(input_identity, input_password)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong login credentials");
            alert.setHeaderText("We couldn't find an account with that username.\n" +
                    "Please check your credentials and try again.");
            alert.showAndWait();
            return;
        }
        DataManager.setCurrentUser(DataManager.retrieveUser(input_identity));
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Welcome back ya m3lm");
        alert.setHeaderText("Welcome back ya m3lm");
        var result = alert.showAndWait();
        if(result.isPresent()) {
            if(result.get()== ButtonType.OK) {
                HomePageController.switchToHomePage(event);
            }
        }
    }

    /**
     * Navigates to the account creation scene.
     *
     * @param event The ActionEvent triggering the scene switch.
     * @throws IOException If an I/O error occurs during scene switching.
     */
    public void createNewAccount(ActionEvent event) throws IOException {
        WelcomeLogin.switchToScene(event, "CreateAccountScene.fxml");
    }

    /**
     * Gets the current user's identity.
     *
     * @return The current user's identity.
     */
    public static String getCurrentUserIdentity() {
        return CurrentUserIdentity;
    }
}
