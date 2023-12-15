package Post;

import dataManager.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

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
    boolean liked;
    private Comment comment;

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

    private void setLikesCounterLabel() {
        likes_counter.setText(Integer.toString(comment.getReactsCounter()));
    }

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
