package Post;

import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

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
    private Post post;
    boolean liked;

    public void setData(Post post) {
        this.post = post;
        privacy.setText(post.getPrivacy());
        caption.setText(post.getCaption());
        date.setText(post.getCreatedOn().toString());
        name.setText(post.getCreatorName());
        username.setText(post.getCreatorUsername());
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
//        Comment comment = new Comment();
    }

    public void switch_to_comment_scene(ActionEvent event) throws IOException {
        Starter.switchToScene(event, "CommentsScene.fxml");
    }
}