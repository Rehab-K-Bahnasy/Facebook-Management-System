package userDashaboard;

import dataManager.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SearchController {
    @FXML
    private TextField search_bar;
    @FXML
    private ListView<NameUsername> list_view;
    private static final ArrayList<User> users = DataManager.getUsers();

    @FXML
    private void search() {
        list_view.setVisible(true);
        list_view.getItems().clear();
        var search_results = searchResults(search_bar.getText());
        list_view.getItems().addAll(search_results);
    }

    @FXML
    private void goToProfile(ActionEvent event) throws IOException {
        String username = list_view.getSelectionModel().getSelectedItem().username;

        ProfileController.switchToProfile(event, DataManager.retrieveUser(username));
    }

    private List<NameUsername> searchResults(String search_word) {
        List<NameUsername> results = new ArrayList<>();
        for (var user : users) {
            if (user.hasSimilarities(search_word)) {
                results.add(new NameUsername(user.getName(), user.getUsername()));
            }
        }
        return results;
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        HomePageController.switchToHomePage(event);
    }

    public static void switchToSearch(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource("SearchScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}