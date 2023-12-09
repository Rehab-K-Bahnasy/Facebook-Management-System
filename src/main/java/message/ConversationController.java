package message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import java.util.stream.Collectors;

public class ConversationController {




    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("Eman Saleh", "Salma", "Rehab", "Heba", "Omnia",
                    "Friends", "Animal", "Human", "Humans", "Alhassan", "Life",
                    "Zedan", "Abdelrahman", "222", "Amr Yasser", "Dog", "Bakry",
                    "Subscribe!", "SoftwareEngineeringStudent", "You got this!!",
                    "Super Human", "Super", "Like")
    );

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;


    @FXML
    void search(ActionEvent event) { // button
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(), words));
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            // Open a new window when a specific item is selected
            openNewWindow(newValue);

        });
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(words);

    }
    private void openNewWindow(String message) {
        Stage newStage = new Stage();
        StackPane newRoot = new StackPane();
        newRoot.getChildren().add(new javafx.scene.control.Label(message));
        Scene newScene = new Scene(newRoot, 500, 400);
        newStage.setTitle("a friend chat");
        newStage.setScene(newScene);
        newStage.show();
    }
    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }



}