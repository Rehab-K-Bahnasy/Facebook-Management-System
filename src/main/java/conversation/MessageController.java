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
import userDashaboard.HomePageController;
import userDashaboard.SettingsController;

import java.io.IOException;
import java.util.*;

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

    public void initialize() {
        for (var friend : DataManager.getCurrentUser().getAllFriends())
            this.friends_combo_box.getItems().add(friend.getUsername());
        this.sender_username = DataManager.getCurrentUser().getUsername();
        this.sent = DataManager.getCurrentUser().getSent_messages();
        this.received = DataManager.getCurrentUser().getReceived_message();
        for (Message message : sent) {
            sent_combo_box.getItems().addAll((int)message.getId());
        }
        for (Message message : received) {
            received_combo_box.getItems().addAll((int)message.getId());
        }
        sent_combo_box.setOnAction(event -> handleSentComboBoxSelection());
        received_combo_box.setOnAction(event -> handleReceivedComboBoxSelection());
    }
    @FXML
    public static void switchToMessages(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MessageController.class.getResource("MessageScene.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addToHistory(int message) {
        sent_combo_box.getItems().addAll(message);
        sent_combo_box.setOnAction(event -> handleSentComboBoxSelection());
        received_combo_box.setOnAction(event -> handleReceivedComboBoxSelection());
    }
    public void NewMessageButton(ActionEvent e) {
        message_content.setText(null);
        message_content.setEditable(true);
        send_button.setDisable(false);
    }

    public void SendButton(ActionEvent e) {
        sent.add(new Message(message_content.getText(), sender_username, recipients_usernames));
        NewMessageButton(new ActionEvent());
        addToHistory(sent.getLast().getId());
        User.updateSentMessages(sender_username, sent.getLast());
        for (var user : recipients_usernames)
            User.updateReceivedMessages(user, sent.getLast());
    }
    @FXML
    private void handleFriendsComboBoxSelection() {
        recipients_usernames.add((String) friends_combo_box.getValue());
    }
    @FXML
    private void handleSentComboBoxSelection() {
        int selectedItem = (int) sent_combo_box.getValue();
        if ((Integer) selectedItem == (Integer) null) {return;}

        for (Message message : sent) {
            if (message.getId() == (selectedItem)) {
                message_content.setText(message.getContent());
                break;
            }
        }
        message_content.setEditable(false);
    }
    @FXML
    private void handleReceivedComboBoxSelection() {
        int selectedItem = (int) received_combo_box.getValue();
        if ((Integer) selectedItem == (Integer) null) {return;}

        for (Message message : received) {
            if (message.getId() == (selectedItem)) {
                message_content.setText(message.getContent());
                break;
            }
        }
        message_content.setEditable(false);
    }
    @FXML
    private void backToHomepage(ActionEvent event) throws IOException {
        HomePageController.switchToHomePage(event);
    }
}