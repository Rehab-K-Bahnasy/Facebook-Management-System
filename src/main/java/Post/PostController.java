package Post;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class PostController {
    @FXML
    Label name;
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
    public Post post= new Post(0,"hello world",LocalDate.now(),"public");
    public void setPost(Post post) {
        this.post = post;
    }

    private void setNameLabel() {
        name.setText(post.getCaption());
    }

    private void setUsernameLabel() {
        username.setText(post.getUsername());
    }

    private void setPrivacyLabel() {
        privacy.setText(post.getPrivacy());
    }

    private void setCaptionLabel() {
        caption.setText(post.getCaption());
    }

    private void setDateLabel() {
        date.setText(post.getCreatedOn());
    }

    private void setLikesCounterLabel() {
        likes_counter.setText(Integer.toString(post.getReacts()));
    }

    private void setCommentsCounterLabel() {
        likes_counter.setText(Integer.toString(post.getCommentsCounter()));
    }

    @FXML
    private void changeLiked()  {
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
        post.addComment(new Comment(username.getText(),0,post.getID()));
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
   public void switch_to_comment_scene(ActionEvent event) throws IOException {
        Starter.switchToScene(event,"CommentsScene.fxml");
   }

}