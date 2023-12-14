package userDashaboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MessagingController extends SearchController {
//    private void openNewWindow(String message) {
//        Stage newStage = new Stage();
//        StackPane newRoot = new StackPane();
//        newRoot.getChildren().add(new javafx.scene.control.Label(message));
//        Scene newScene = new Scene(newRoot, 500, 400);
//        newStage.setTitle("a friend chat");
//        newStage.setScene(newScene);
//        newStage.show();
//    }
    public static void switchToMessage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MessagingController.class.getResource("MessagingScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}