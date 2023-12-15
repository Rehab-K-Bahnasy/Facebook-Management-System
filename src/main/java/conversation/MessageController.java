package conversation;

import dataManager.DataManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import user.User;
import userDashboard.HomePageController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The MessageController class is the controller for the message functionality in the Welcome Login application.
 * It handles user interactions related to sending, receiving, and viewing messages.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class MessageController {

    @FXML
    private ComboBox sent_combo_box;
    @FXML
    private ComboBox received_combo_box;
    @FXML
    private ComboBox friends_combo_box;
    @FXML
    private TextArea message_content;
    @FXML
    private Button send_button;
    ArrayList<Message> sent = new ArrayList<>();
    ArrayList<Message> received = new ArrayList<>();
    private String sender_username;
    private ArrayList<String> recipients_usernames = new ArrayList<>();

    /**
     * Initializes the MessageController, setting up the UI components and populating the combo boxes with data.
     */
    public void initialize() {
        for (var friend : DataManager.getCurrentUser().getAllFriends())
            this.friends_combo_box.getItems().add(friend.getUsername());
        this.sender_username = DataManager.getCurrentUser().getUsername();
        this.sent = DataManager.getCurrentUser().getSent_messages();
        this.received = DataManager.getCurrentUser().getReceived_message();
        for (Message message : sent) {
            sent_combo_box.getItems().addAll((int) message.getId());
        }
        for (Message message : received) {
            received_combo_box.getItems().addAll((int) message.getId());
        }
        sent_combo_box.setOnAction(event -> handleSentComboBoxSelection());
        received_combo_box.setOnAction(event -> handleReceivedComboBoxSelection());
    }

    /**
     * Switches the scene to the MessageScene when the "Messages" button is clicked.
     *
     * @param event The event triggered by the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    public static void switchToMessages(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MessageController.class.getResource("MessageScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Adds a message to the history combo box.
     *
     * @param message The ID of the message to be added to the history.
     */
    public void addToHistory(int message) {
        sent_combo_box.getItems().addAll(message);
        sent_combo_box.setOnAction(event -> handleSentComboBoxSelection());
        received_combo_box.setOnAction(event -> handleReceivedComboBoxSelection());
    }

    /**
     * Resets the UI components to allow the user to compose a new message.
     *
     * @param e The event triggered by the button click.
     */
    public void NewMessageButton(ActionEvent e) {
        sent_combo_box.setValue(null);
        received_combo_box.setValue(null);
        message_content.setText(null);
        message_content.setEditable(true);
        send_button.setDisable(false);
    }

    /**
     * Sends a message when the "Send" button is clicked, updating UI components and data accordingly.
     *
     * @param e The event triggered by the button click.
     */
    public void SendButton(ActionEvent e) {
        sent.add(new Message(message_content.getText(), sender_username, recipients_usernames));
        NewMessageButton(new ActionEvent());
        addToHistory(sent.getLast().getId());
        User.updateSentMessages(sender_username, sent.getLast());
        for (var user : recipients_usernames)
            User.updateReceivedMessages(user, sent.getLast());
    }

    /**
     * Handles the selection of a friend in the friends combo box.
     */
    @FXML
    private void handleFriendsComboBoxSelection() {
        recipients_usernames.add((String) friends_combo_box.getValue());
    }

    /**
     * Handles the selection of a sent message in the sent combo box.
     */
    @FXML
    private void handleSentComboBoxSelection() {
        int selectedItem = (int) sent_combo_box.getValue();

        for (Message message : sent) {
            if (message.getId() == (selectedItem)) {
                message_content.setText(message.getContent());
                break;
            }
        }
        message_content.setEditable(false);
    }

    /**
     * Handles the selection of a received message in the received combo box.
     */
    @FXML
    private void handleReceivedComboBoxSelection() {
        int selectedItem = (int) received_combo_box.getValue();

        for (Message message : received) {
            if (message.getId() == (selectedItem)) {
                message_content.setText(message.getContent());
                break;
            }
        }
        message_content.setEditable(false);
    }

    /**
     * Switches the scene back to the home page when the "Back" button is clicked.
     *
     * @param event The event triggered by the button click.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    private void backToHomepage(ActionEvent event) throws IOException {
        HomePageController.switchToHomePage(event);
    }
}
