package Post;

import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CommentController {
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
    @FXML
    private Label replies_counter;
    @FXML
    private TextField reply_field;
    boolean liked;
    Comment comment;

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

    @FXML
    private void addReply() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(reply_field.getText().isEmpty()){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Empty reply");
            alert.setHeaderText("Cannot reply with an empty comment");
            alert.showAndWait();
            return;
        }
        Comment reply = new Comment(reply_field.getText());
        comment.addReply(reply);
        alert.setTitle("Reply sent");
        alert.setHeaderText("Your Reply has been uploaded");
        alert.showAndWait();
    }

    public void switch_to_replies_scene(ActionEvent event) throws IOException {
        RepliesController.setComment(comment);
        RepliesController.switchToRepliesScene(event);
    }
}
