package Post;

import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * The {@code CommentController} class controls the behavior of the UI elements
 * for displaying and interacting with a single comment in a post.
 * It handles actions such as liking the comment, adding replies, and navigating to the replies scene.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class CommentController {

    /** The container for UI elements representing a comment. */
    @FXML
    private VBox box;

    /** The label displaying the comment content. */
    @FXML
    private Label caption;

    /** The label displaying the username of the comment creator. */
    @FXML
    private Label username;

    /** The label displaying the name of the comment creator. */
    @FXML
    private Label name;

    /** The button for liking or unliking the comment. */
    @FXML
    private ToggleButton like;

    /** The label displaying the number of likes on the comment. */
    @FXML
    private Label likes_counter;

    /** The label displaying the number of replies to the comment. */
    @FXML
    private Label replies_counter;

    /** The text field for entering a reply to the comment. */
    @FXML
    private TextField reply_field;

    /** Flag indicating whether the current user has liked the comment. */
    private boolean liked;

    /** The comment associated with this controller. */
    private Comment comment;

    /**
     * Sets the data for the comment to be displayed and initializes the UI elements.
     *
     * @param comment The comment to be displayed.
     */
    public void setData(Comment comment) {
        this.comment = comment;
        caption.setText(comment.getComment_content());
        username.setText(comment.getUsername());
        name.setText(comment.getName());
        likes_counter.setText(Integer.toString(comment.getReactsCounter()));
        replies_counter.setText(Integer.toString(comment.getRepliesCounter()));
        liked = comment.hasUserLikedComment(DataManager.getCurrentUser());
        if (liked) {
            like.setStyle("-fx-background-color: #35502c");
            like.setText("Liked");
        } else {
            like.setStyle("-fx-background-color: #709354");
            like.setText("Like");
        }
    }

    /**
     * Sets the label displaying the number of likes on the comment.
     */
    private void setLikesCounterLabel() {
        likes_counter.setText(Integer.toString(comment.getReactsCounter()));
    }

    /**
     * Handles the action of changing the liked status of the comment.
     * Updates the UI elements accordingly and adds or removes the user's reaction.
     */
    @FXML
    private void changeLiked() {
        liked = !liked;
        if (liked) {
            like.setStyle("-fx-background-color: #35502c");
            like.setText("Liked");
        } else {
            like.setStyle("-fx-background-color: #709354");
            like.setText("Like");
        }
        if (liked) {
            comment.addReact(DataManager.getCurrentUser());
        } else {
            comment.removeReact(DataManager.getCurrentUser());
        }
        setLikesCounterLabel();
    }

    /**
     * Handles the action of adding a reply to the comment.
     * Displays an alert if the reply field is empty and adds the reply otherwise.
     */
    @FXML
    private void addReply() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(reply_field.getText().isEmpty()){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Empty reply");
            alert.setHeaderText("Cannot post an empty post");
            alert.showAndWait();
            return;
        }
        Comment reply = new Comment(reply_field.getText());
        comment.addReply(reply);
        alert.setTitle("Reply sent");
        alert.setHeaderText("Your Reply has been uploaded");
        alert.showAndWait();
    }

    /**
     * Switches to the replies scene for the associated comment.
     *
     * @param event The action event triggering the scene switch.
     * @throws IOException If an I/O error occurs during the scene switch.
     */
    public void switch_to_replies_scene(ActionEvent event) throws IOException {
        RepliesController.setComment(comment);
        RepliesController.switchToRepliesScene(event);
    }
}