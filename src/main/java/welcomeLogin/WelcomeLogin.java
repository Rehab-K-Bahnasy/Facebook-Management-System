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
    public static void switchToScene(ActionEvent event,String file) throws IOException {
        fxmlLoader = new FXMLLoader(WelcomeLogin.class.getResource(file));
        root = fxmlLoader.load();
        scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException {
        DataReader.readUsersData();
        launch();
        DataWriter.writeUsersData();
    }
}