package conversation;

import dataManager.DataReader;
import dataManager.DataWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import welcomeLogin.WelcomeLogin;

import java.io.IOException;

/**
 * The Main class serves as the entry point for the Welcome Login application.
 * It extends the JavaFX Application class to provide the necessary setup for the JavaFX user interface.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class Main extends Application {

    private static Parent root;
    private static Scene scene;
    private static FXMLLoader fxmlLoader;

    /**
     * The main entry point for the Welcome Login application.
     * It reads user data from the data file, launches the JavaFX application, and writes user data back after execution.
     *
     * @param args The command line arguments.
     * @throws IOException if an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        DataReader.readUsersData();
        launch();
        DataWriter.writeUsersData();
    }

    /**
     * Overrides the start method of the Application class.
     * It sets up the JavaFX stage, loads the initial FXML file, and displays the primary scene.
     *
     * @param stage The primary stage for the Welcome Login application.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("MessageScene.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root, 1024, 720);
        stage.setTitle("SOCIO");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
