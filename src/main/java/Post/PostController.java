package Post;

import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class PostController {
    @FXML
    private Label name;
    @FXML
    private Label username;
    @FXML
    private Label privacy;
    @FXML
    private Label caption;
    @FXML
    private Label date;
    @FXML
    private ToggleButton like;
    @FXML
    private Label likes_counter;
    @FXML
    private Label comments_counter;
    @FXML
    private TextField comment_field;
    @FXML
    private Label tagged;
    private Post post;
    boolean liked;

    public void setData(Post post) {
        this.post = post;
        privacy.setText(post.getPrivacy());
        caption.setText(post.getCaption());
        date.setText(post.getCreatedOn().toString());
        name.setText(post.getCreatorName());
        username.setText("@" + post.getCreatorUsername());
        likes_counter.setText(Integer.toString(post.getReacts()));
        comments_counter.setText(Integer.toString(post.getCommentsCounter()));
        liked = post.hasUserLikedPost(DataManager.getCurrentUser());
        if (liked) {
            like.setStyle("-fx-background-color: #35502c");
            like.setText("Liked");
        } else {
            like.setStyle("-fx-background-color: #709354");
            like.setText("Like");
        }
        var tags = post.getTaggedUsers();
        if (!tags.isEmpty()) {
            StringBuilder tag_message = new StringBuilder("and");
            for (int i = 0; i < Math.min(2, tags.size()); i++) {
                tag_message.append(" ").append(tags.get(i).getUsername());
            }
            if (tags.size() > 2) {
                tag_message.append(" and others");
            }
            tagged.setText(tag_message.toString());
            tagged.setVisible(true);
        }
    }

    public void setPost(Post post) {
        this.post = post;
    }

    private void setLikesCounterLabel() {
        likes_counter.setText(Integer.toString(post.getReacts()));
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
            post.addReact(DataManager.getCurrentUser());
        } else {
            post.removeReact(DataManager.getCurrentUser());
        }
        setLikesCounterLabel();
    }

    @FXML
    private void addComment() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (comment_field.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Empty comment");
            alert.setHeaderText("Cannot post an empty comment");
            alert.showAndWait();
            return;
        }
        Comment comment = new Comment(comment_field.getText());
        post.addComment(comment);
        alert.setTitle("Comment sent");
        alert.setHeaderText("Your comment has been uploaded");
        alert.showAndWait();
    }

    public void switch_to_comment_scene(ActionEvent event) throws IOException {
        CommentsController.setPost(post);
        CommentsController.switchToCommentsScene(event);
    }
}