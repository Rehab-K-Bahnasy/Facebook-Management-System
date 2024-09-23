/**
 * The `WelcomeLogin` class is the entry point for the application and manages the welcome/login scene.
 * Extends the JavaFX `Application` class, providing the start method for initializing the graphical user interface.
 * Handles scene transitions and application launch, including data reading and writing operations.
 *
 * This class is associated with the "WelcomeScene.fxml" FXML file, which represents the initial welcome/login interface.
 * It includes methods for switching scenes, initializing the application window, and launching the application.
 * The `DataReader` and `DataWriter` classes are utilized for reading and writing user data, respectively.
 *
 * @author SOC-IO
 * @version 1.0
 */
package welcomeLogin;

import dataManager.DataReader;
import dataManager.DataWriter;
import javafx.scene.control.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;

public class WelcomeLogin extends Application {

    private static Parent root;
    private static Scene scene;
    private static FXMLLoader fxmlLoader;

    /**
     * Initializes the graphical user interface, sets up the initial scene, and launches the application.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If an I/O error occurs during FXML loading.
     */
    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(WelcomeLogin.class.getResource("WelcomeScene.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root, 1024, 720);
        stage.setTitle("SOCIO");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Switches to the specified FXML scene file.
     *
     * @param event The ActionEvent triggering the scene switch.
     * @param file  The FXML file representing the target scene.
     * @throws IOException If an I/O error occurs during FXML loading.
     */
    public static void switchToScene(ActionEvent event, String file) throws IOException {
        fxmlLoader = new FXMLLoader(WelcomeLogin.class.getResource(file));
        root = fxmlLoader.load();
        scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main entry point for the application.
     *
     * @param args The command-line arguments.
     * @throws IOException If an I/O error occurs during data reading or writing.
     */
    public static void main(String[] args) throws IOException {
        DataReader.readUsersData();
        launch();
        DataWriter.writeUsersData();
    }
}
