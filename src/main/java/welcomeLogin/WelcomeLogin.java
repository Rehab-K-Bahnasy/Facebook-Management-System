package welcomeLogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeLogin extends Application {
    public static Stage mainStage;
    public static Parent root;
    public static Scene scene;
    public static FXMLLoader fxmlLoader;
    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(WelcomeLogin.class.getResource("WelcomeScene.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root, 1024, 720);
        stage.setTitle("SOCIO");
        stage.setScene(scene);
        stage.setResizable(false);
        mainStage = stage;
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}