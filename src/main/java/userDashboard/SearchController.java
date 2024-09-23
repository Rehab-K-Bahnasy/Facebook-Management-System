package userDashboard;

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


/**
 * The controller class for the Search functionality in the user dashboard.
 *
 * @version 1.0
 * @author SOC-IO
 */
public class SearchController {

    /**
     * The text field for user input in the search bar.
     */
    @FXML
    private TextField search_bar;

    /**
     * The list view to display search results.
     */
    @FXML
    private ListView<NameUsername> list_view;

    // The list of users obtained from the DataManager
    private static final ArrayList<User> users = DataManager.getUsers();

    /**
     * Handles the search functionality triggered by the user.
     * Populates the list view with search results.
     */
    @FXML
    private void search() {
        list_view.setVisible(true);
        list_view.getItems().clear();
        var search_results = searchResults(search_bar.getText());
        list_view.getItems().addAll(search_results);
    }

    /**
     * Handles the action to navigate to the profile of the selected user.
     *
     * @param event The event triggered by the user action.
     * @throws IOException If an error occurs during navigation to the profile.
     */
    @FXML
    private void goToProfile(ActionEvent event) throws IOException {
        var selectedItem = list_view.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            var user = DataManager.retrieveUser(selectedItem.username);
            ProfileController.setUser(user);
            ProfileController.switchToProfile(event, user);
        }
    }

    /**
     * Generates a list of search results based on the given search word.
     *
     * @param search_word The word used for searching users.
     * @return List of NameUsername objects representing search results.
     */
    private List<NameUsername> searchResults(String search_word) {
        List<NameUsername> results = new ArrayList<>();
        for (var user : users) {
            if (user.hasSimilarities(search_word)) {
                results.add(new NameUsername(user.getName(), user.getUsername()));
            }
        }
        return results;
    }

    /**
     * Handles the action to navigate back to the home page.
     *
     * @param event The event triggered by the user action.
     * @throws IOException If an error occurs during navigation back to the home page.
     */
    @FXML
    private void back(ActionEvent event) throws IOException {
        HomePageController.switchToHomePage(event);
    }

    /**
     * Switches the scene to the Search scene.
     *
     * @param event The event triggered by the user action.
     * @throws IOException If an error occurs during scene switching.
     */
    public static void switchToSearch(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource("SearchScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
