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
import java.util.ArrayList;

public class MessageController {

    @FXML
    private Label to_label;
    @FXML
    private ComboBox choose_a_message;
    @FXML
    private TextArea message_content;
    @FXML
    private Button new_message_button;
    @FXML
    private Button send_button;
    ArrayList<Message> messages;
    private String sender_username;
    private ArrayList<String> recipients_usernames;

    public void initialize(String sender_username, ArrayList<String> recipients_usernames, ArrayList<Message> messages) {
        this.sender_username = sender_username;
        this.recipients_usernames = recipients_usernames;
        this.messages = messages;
        for (Message message : messages) {
            if (message.getSenderUsername().equals(sender_username)) {
                if (message.getRecipientsUsernames().equals(recipients_usernames)) {
                    choose_a_message.getItems().add(message.getContent());
                    continue;
                }
            }
            for (String recipient : message.getRecipientsUsernames()) {
                if (sender_username.equals(recipient)) {
                    choose_a_message.getItems().add(message.getContent());
                    break;
                }
            }
        }
        choose_a_message.setOnAction(event -> handleChoiceBoxSelection());
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
    public void addToHistory(String message) {
        choose_a_message.getItems().add(message);
        choose_a_message.setOnAction(event -> handleChoiceBoxSelection());
    }
    public void NewMessageButton(ActionEvent e) {
        choose_a_message.setValue(null);
        message_content.setText(null);
        message_content.setEditable(true);
        send_button.setDisable(false);
    }

    public void SendButton(ActionEvent e) {
        messages.add(new Message(message_content.getText(), sender_username, recipients_usernames));
        NewMessageButton(new ActionEvent());
        addToHistory(messages.getLast().getDate().toString());
    }
    @FXML
    private void handleChoiceBoxSelection() {
        String selectedItem = (String) choose_a_message.getValue();

        for (Message message : messages) {
            if (message.getContent().equals(selectedItem)) {
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