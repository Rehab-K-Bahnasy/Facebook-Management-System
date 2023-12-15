package Post;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import userDashaboard.HomePageController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RepliesController implements Initializable {
    @FXML
    private VBox cardLayoout;
    private List<Comment> recentlyAdded;
    private static Comment comment;
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        recentlyAdded = new ArrayList<>(recentlyAdded());

        try {
            for (int i= 0; i < recentlyAdded.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation (getClass().getResource ( "ReplyScene.fxml"));
                VBox cardBox = fxmlLoader.load();
                ReplyController replyController= fxmlLoader.getController();
                replyController.setData(recentlyAdded.get(i));
                cardLayoout.getChildren().add(cardBox);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Comment> recentlyAdded () {
        return comment.getComment_reply();
    }
    public static void setComment(Comment _comment) {
        comment = _comment;
    }
    public void switch_to_comments_scene(ActionEvent event) throws IOException {
        Starter.switchToScene(event,"CommentsScene.fxml");
    }
    @FXML
    private void back(ActionEvent event) throws IOException{
        HomePageController.switchToHomePage(event);
    }
    public static void switchToRepliesScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CommentsController.class.getResource("RepliesScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
