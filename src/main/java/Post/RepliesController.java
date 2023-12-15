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
import userDashboard.HomePageController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The RepliesController class is responsible for managing the UI and user interactions in the replies section
 * of a comment in the SOCIO application.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class RepliesController implements Initializable {
   @FXML
    public VBox cardLayoout;
    private List<Comment> recentlyAdded;
    private static Comment comment;

    /**
     * Initializes the RepliesController, populating the UI with recent replies to the associated comment.
     *
     * @param location  The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recentlyAdded = new ArrayList<>(recentlyAdded());

        try {
            for (int i = 0; i < recentlyAdded.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ReplyScene.fxml"));
                VBox cardBox = fxmlLoader.load();
                ReplyController replyController = fxmlLoader.getController();
                replyController.setData(recentlyAdded.get(i));
                cardLayoout.getChildren().add(cardBox);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the list of recently added replies to the associated comment.
     *
     * @return List of recently added replies.
     */
    private List<Comment> recentlyAdded() {
        return comment.getComment_reply();
    }

    /**
     * Sets the associated comment for which replies are being displayed.
     *
     * @param _comment The comment associated with the replies.
     */
    public static void setComment(Comment _comment) {
        comment = _comment;
    }

    /**
     * Switches to the comments scene when the associated button is clicked.
     *
     * @param event The event triggered by the button click.
     * @throws IOException If an I/O error occurs.
     */
    public void switchToCommentsScene(ActionEvent event) throws IOException {
        Starter.switchToScene(event, "CommentsScene.fxml");
    }

    /**
     * Navigates back to the home page when the associated button is clicked.
     *
     * @param event The event triggered by the button click.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void back(ActionEvent event) throws IOException {
        HomePageController.switchToHomePage(event);
    }

    /**
     * Switches to the replies scene when the associated button is clicked.
     *
     * @param event The event triggered by the button click.
     * @throws IOException If an I/O error occurs.
     */
    public static void switchToRepliesScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CommentsController.class.getResource("RepliesScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
