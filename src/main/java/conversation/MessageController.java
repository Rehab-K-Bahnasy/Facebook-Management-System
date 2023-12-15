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
import userDashaboard.HomePageController;
import userDashaboard.SettingsController;

import java.io.IOException;
import java.util.*;

public class MessageController {

    @FXML
    private Label to_label;
    @FXML
    private ComboBox sent_combo_box;
    @FXML
    private ComboBox received_combo_box;
    @FXML
    private TextArea message_content;
    @FXML
    private Button new_message_button;
    @FXML
    private Button send_button;
    ArrayList<Message> sent = new ArrayList<>();
    ArrayList<Message> received = new ArrayList<>();
    private String sender_username;
    private ArrayList<String> recipients_usernames = new ArrayList<>();

    public void initialize(String sender_username, ArrayList<String> recipients_usernames, ArrayList<Message> sent, ArrayList<Message> received) {
        this.sender_username = sender_username;
        this.recipients_usernames = recipients_usernames;
        this.sent = sent;
        this.received = received;
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
        sent_combo_box.setValue(null);
        received_combo_box.setValue(null);
        message_content.setText(null);
        message_content.setEditable(true);
        send_button.setDisable(false);
    }

    public void SendButton(ActionEvent e) {
        sent.add(new Message(message_content.getText(), sender_username, recipients_usernames));
        NewMessageButton(new ActionEvent());
        addToHistory(sent.getLast().getId());
    }
    @FXML
    private void handleSentComboBoxSelection() {
        int selectedItem = (int) sent_combo_box.getValue();

        for (Message message : sent) {
            System.out.println(message.getContent());
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