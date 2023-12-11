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
    public void setToLabel(String to_user) {
        to_label.setText(to_user);
    }

    public void initialize(User sender, User reciever) {

        choose_a_message.getItems().addAll();
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
        messages.add(new Message((byte)(messages.size() + 1) ,message_content.getText()));
        NewMessageButton(new ActionEvent());
        addToHistory(messages.getLast().getDate().toString());
    }
    @FXML
    private void handleChoiceBoxSelection() {
        String selectedItem = (String) choose_a_message.getValue();
        System.out.println("Selected item: " + selectedItem);

        for (Message message : messages) {
            System.out.println("Content is" + selectedItem);
            if (message.getDate().toString().equals(selectedItem)) {
                message_content.setText(message.getContent());
                break;
            }
        }
        message_content.setEditable(false);
    }
}