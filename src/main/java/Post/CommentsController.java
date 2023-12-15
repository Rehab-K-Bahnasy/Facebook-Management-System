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
import userDashaboard.CreatPostController;
import userDashaboard.HomePageController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util. ResourceBundle;


public class CommentsController implements Initializable {
    @FXML
    private VBox cardLayoout;
    private List<Comment> recentlyAdded;
    private static Post post;
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        try {
            for (int i= 0; i < recentlyAdded.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation (Post.class.getResource("CommentScene.fxml"));
                VBox cardBox = fxmlLoader.load();
                CommentController commentController= fxmlLoader.getController();
                commentController.setData(recentlyAdded.get(i));
                cardLayoout.getChildren().add(cardBox);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private List<Comment> recentlyAdded () {
        return post.getComments_on_post();
    }
    public static void setPost(Post _post) {
        post = _post;
    }
    public void switch_to_post_scene(ActionEvent event) throws IOException {
        Starter.switchToScene(event,"PostScene.fxml");
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
        HomePageController.switchToHomePage(event);
    }

    public static void switchToCommentsScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CommentsController.class.getResource("CommentsScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}