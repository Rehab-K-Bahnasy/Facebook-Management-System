package Posts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class PostController {
    @FXML
    Label nameLabel;
    @FXML
    Label usernameLabel;
    @FXML
    Label privacyLabel;
    @FXML
    Label captionLabel;
    @FXML
    Label dateLabel;
    @FXML
    ToggleButton likeButton;
    @FXML
    Label likesCounterLabel;
    @FXML
    Label commentsCounterLabel;
    Parent root;
    Stage stage;
    Scene comment_scene;
    boolean liked = false;
    public static Post post= new Post(0,"hello world",LocalDate.now(),"public");

    private void setNameLabel() {
        nameLabel.setText(post.getCaption());
    }

    private void setUsernameLabel() {
        usernameLabel.setText(post.getUsername());
    }

    private void setPrivacyLabel() {
        privacyLabel.setText(post.getPrivacy());
    }

    private void setCaptionLabel() {
        captionLabel.setText(post.getCaption());
    }

    private void setDateLabel() {
        dateLabel.setText(post.getCreatedOn());
    }

    private void setLikesCounterLabel() {
        likesCounterLabel.setText(Integer.toString(post.getReacts()));
    }

    private void setCommentsCounterLabel() {
        likesCounterLabel.setText(Integer.toString(post.getCommentsCounter()));
    }

    @FXML
    private void changeLiked() {
        liked = !liked;
        if (liked) {
            likeButton.setStyle("-fx-background-color: #35502c");
            likeButton.setText("Liked");
        } else {
            likeButton.setStyle("-fx-background-color: #709354");
            likeButton.setText("Like");
        }
        try {
            post.modifyReacts(liked);
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        setLikesCounterLabel();
    }

    public void setThePost() {
        setNameLabel();
        setUsernameLabel();
        setPrivacyLabel();
        setCaptionLabel();
        setDateLabel();
        setLikesCounterLabel();
        setCommentsCounterLabel();
    }

//    /*public void switchtofeedscene(ActionEvent event) throws IOException {
//       Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("feed.fxml")));
//       stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//       feed_scene=new Scene(root);
//       stage.setScene(feed_scene);
//       stage.show();
//    }
   @FXML
    public void switchtocommentscene(ActionEvent event) throws IOException {
        HelloApplication.switchToScene(event,"CommentScene.fxml");
//       FXMLLoader loader =  new FXMLLoader(Comment.class.getResource("CommentScene.fxml"));
//       root = loader.load();
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         comment_scene= new Scene(root);
//        stage.setScene(comment_scene);
//        stage.show();
   }

}