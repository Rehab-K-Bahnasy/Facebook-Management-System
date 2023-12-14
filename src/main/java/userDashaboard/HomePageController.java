package userDashaboard;
import  Post.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import welcomeLogin.WelcomeLogin;
import javafx.scene.layout.*;
import java.io.IOException;

public class HomePageController implements Initializable {
   ////////////emaa
   @FXML
    private VBox postscontainer;
    List<Post>postss;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                  postss=new ArrayList<>(getposts());
                  try
                  {
                      for(Post p:postss)
                      {
                           FXMLLoader fxmlLoader=new FXMLLoader();
                           fxmlLoader.setLocation(getClass().getResource("fxml/HomePageScene.fxml"));
                           VBox vbox=fxmlLoader.load();
                           PostController postController=fxmlLoader.getController();
                           postController.setThePost(p);

                          postscontainer.getChildren().add(vbox);
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


    ///////////emaa
    @FXML
    private void settings(ActionEvent event) throws IOException {
        SettingsController.switchToSettings(event);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("log out");
        alert.setHeaderText("You're about to leave :(");
        alert.setContentText("Are you sure you want to logout?");
        var result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                WelcomeLogin.switchToScene(event, "WelcomeScene.fxml");
            }
        }
    }

    public static void switchToHomePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("HomePageScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
