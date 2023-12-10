package Post;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CommentController {
    @FXML
    private VBox box;
    @FXML
    private Label caption;
    @FXML
    private Label user;

    public void setData(Comment comment)
    {
        caption.setText(comment.getComment_content());
        user.setText(comment.getUsername());
    }
}
