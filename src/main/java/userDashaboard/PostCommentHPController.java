package userDashaboard;

import Post.Post;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import  Post.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;
import javafx.scene.layout.*;
import java.util.ResourceBundle;

public class PostCommentHPController implements Initializable {

    @FXML
    private List<Post> postss;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postss=new ArrayList<>(getposts());
        try
        {
            for(Post p:postss)
            {
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("PostScene.fxml"));
                VBox vbox=fxmlLoader.load();
               PostController postcontroller=fxmlLoader.getController(); ///the use
               postcontroller.setData(p);
               HomePageController.postscontainer.getChildren().add(vbox);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public List<Post> getposts(){

        List<Post> listpost=new ArrayList<>();
        Post post;
        for(int i=0;i<4;i++)
        {
            post=new Post(312,"hello world", LocalDate.now(),"public");
            listpost.add(post);
        }

        return listpost;
    }



}
