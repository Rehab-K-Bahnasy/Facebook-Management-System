package Post;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.time.LocalDate;

public class PostController {
    @FXML
    Label name;
    @FXML
    VBox vBox;
    @FXML
    Label username;
    @FXML
    Label privacy;
    @FXML
    Label caption;
    @FXML
    Label date;
    @FXML
    ToggleButton like;
    @FXML
    Label likes_counter;
    @FXML
    Label comments_counter;
    @FXML
    private TextField comment_field;
    boolean liked;
    public Post post;

    public void setData(Post p) {
        this.post = p;
        privacy.setText(p.getPrivacy());
        caption.setText(p.getCaption());
        date.setText(p.getCreatedOn());
        name.setText(p.getCreatorName());
        username.setText(p.getCreatorUsername());
        likes_counter.setText(Integer.toString(p.getReacts()));
        comments_counter.setText(Integer.toString(p.getCommentsCounter()));
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
        try {
            post.modifyReacts(liked);
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        setLikesCounterLabel();
    }

    @FXML
    private void addComment() {
        post.addComment(new Comment(username.getText(), 0, post.getID()));
    }

//    public void setThePost(Post p) {
//       // setNameLabel();
//       // setUsernameLabel();
//        DateLabel.settext(p.getCreatedon());
//        setPrivacyLabel(p.getPrivacy());
//        setCaptionLabel(p.getCaption());
//        setDateLabel(p.getCreatedOn());
//        //(p.getID());
//       // setLikesCounterLabel();
//       // setCommentsCounterLabel();
//    }

    public void switch_to_comment_scene(ActionEvent event) throws IOException {
        Starter.switchToScene(event, "CommentsScene.fxml");
    }

}