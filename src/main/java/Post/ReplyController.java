package Post;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
    private Button like;
    @FXML
    private Label likes_counter;
    boolean liked;
    Comment comment;

    public void setData(Comment comment)
    {
        this.comment=comment;
        caption.setText(comment.getComment_content());
        username.setText(comment.getUsername());
        name.setText(comment.getName());
    }
    private void setLikesCounterLabel() {
        likes_counter.setText(Integer.toString(comment.getReacts()));
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
            comment.modifyReacts(liked);
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        setLikesCounterLabel();
    }

}
