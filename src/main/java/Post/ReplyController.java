package Post;

import dataManager.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * The ReplyController class is the controller for handling individual comment replies in the SOCIO application.
 * It manages the display and interaction with reply data, including likes and reactions.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class ReplyController {
    @FXML
    private VBox box;
    @FXML
    private Label caption;
    @FXML
    private Label username;
    @FXML
    private Label name;
    @FXML
    private ToggleButton like;
    @FXML
    private Label likes_counter;
    private boolean liked;
    private Comment comment;

    /**
     * Sets the data for the reply, populating the UI elements with the corresponding comment details.
     *
     * @param comment The comment associated with this reply.
     */
    public void setData(Comment comment) {
        this.comment = comment;
        caption.setText(comment.getComment_content());
        username.setText(comment.getUsername());
        name.setText(comment.getName());
        likes_counter.setText(Integer.toString(comment.getReactsCounter()));
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
     * Updates the likes counter label with the current number of reactions.
     */
    private void setLikesCounterLabel() {
        likes_counter.setText(Integer.toString(comment.getReactsCounter()));
    }

    /**
     * Handles the change in the "Liked" state when the user interacts with the like button.
     * Updates the UI and data accordingly.
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
}
