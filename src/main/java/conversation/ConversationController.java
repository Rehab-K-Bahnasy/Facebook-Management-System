package conversation;
import conversation.MessageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import welcomeLogin.WelcomeLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import java.util.stream.Collectors;

public class ConversationController {
    ArrayList<String> words;

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;
    private ArrayList<String> send_to = new ArrayList<String>();
    @FXML
    void search(ActionEvent event) { // button
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(), words));
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            // Open a new window when a specific item is selected
            addButton(newValue);

        });
    }

    public void initialize(ArrayList<String> usernames) {
        this.words = usernames;
        listView.getItems().addAll(words);
    }

    public void addButton(String username) {
        send_to.add(username);
    }
    @FXML
    public static void switchToScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MessageController.class.getResource("Message.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }



}