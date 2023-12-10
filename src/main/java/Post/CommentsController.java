package Post;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util. ResourceBundle;


public class CommentsController implements Initializable {

    @FXML
    private VBox cardLayoout;
    private List<Comment> recentlyAdded;
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        recentlyAdded = new ArrayList<>(recentlyAdded());

        try {
            for (int i= 0; i < recentlyAdded.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation (getClass().getResource ( "CommentScene.fxml"));
                VBox cardBox = fxmlLoader.load();
                CommentController commentController= fxmlLoader.getController();
                commentController.setData(recentlyAdded.get(i));
                cardLayoout.getChildren().add(cardBox);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private List<Comment> recentlyAdded () {
        List<Comment> commentList = new ArrayList<>();
        Comment comment = new Comment("Mobakry",1,1);
       comment.setComment_content("hello hello");
       commentList.add(comment);

        Comment comment2 = new Comment("bakry",2,1);
        comment2.setComment_content("hello hello hello");
        commentList.add(comment2);

        Comment comment3 = new Comment("parlerererererer",3,1);
        comment3.setComment_content("hello bye bye");
        commentList.add(comment3);

        Comment comment4 = new Comment("parlerererererer",4,1);
        comment4.setComment_content("hello bye bye");
        commentList.add(comment4);

        Comment comment5 = new Comment("parlerererererer",5,1);
        comment5.setComment_content("hello bye bye");
        commentList.add(comment5);

        Comment comment6 = new Comment("parlerererererer",6,1);
        comment6.setComment_content("hello bye bye");
        commentList.add(comment6);
       return commentList;
    }
}