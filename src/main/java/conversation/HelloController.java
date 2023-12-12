package conversation;

import conversation.Message;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import user.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController {

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
    private byte sender_id;
    private ArrayList<Byte> recipients_id;

    public void setToLabel(String to_user) {
        to_label.setText(to_user);
    }

    public void initialize(byte sender_id, ArrayList<Byte> recipients_id, ArrayList<Message> messages) {
        this.sender_id = sender_id;
        this.recipients_id = recipients_id;
        this.messages = messages;
        for (Message message : messages) {
            if (message.getSenderId() == sender_id) {
                if (message.getRecipientsId().equals(recipients_id)) {
                    choose_a_message.getItems().add(message.getContent());
                    continue;
                }
            }
            for (Byte recipient : message.getRecipientsId()) {
                if (sender_id == recipient) {
                    choose_a_message.getItems().add(message.getContent());
                    break;
                }
            }
        }
        choose_a_message.setOnAction(event -> handleChoiceBoxSelection());
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
        messages.add(new Message(message_content.getText(), sender_id, recipients_id));
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
}