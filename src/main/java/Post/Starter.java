package Post;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Starter class is the entry point for the Post functionality in the SOCIO application.
 * It extends the JavaFX Application class and provides methods to switch between scenes.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class Starter extends Application {

    /**
     * The root parent node for the current scene.
     */
    private static Parent root;

    /**
     * The current scene.
     */
    private static Scene scene;

    /**
     * The FXMLLoader used to load FXML files.
     */
    private static FXMLLoader fxmlLoader;

    /**
     * Starts the Post application, loading the initial PostScene.
     *
     * @param stage The primary stage for the application.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(Starter.class.getResource("PostScene.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root, 1024, 720);
        stage.setTitle("SOCIO");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Switches the current scene to the one specified by the given FXML file.
     *
     * @param event The event that triggered the scene switch.
     * @param file  The name of the FXML file for the new scene.
     * @throws IOException if an I/O error occurs.
     */
    public static void switchToScene(ActionEvent event, String file) throws IOException {
        fxmlLoader = new FXMLLoader(Starter.class.getResource(file));
        root = fxmlLoader.load();
        scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method to launch the Post application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}
