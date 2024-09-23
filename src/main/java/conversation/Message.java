package conversation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
    int id;
    final private String content;
    final private String sender_username;
    final private ArrayList<String> recipients_usernames;
    private LocalDateTime created_at;

    public Message(String content, String sender_username, ArrayList<String> recipients_usernames) {
        this.content = content;
        this.sender_username = sender_username;
        this.recipients_usernames = recipients_usernames;
        created_at = LocalDateTime.now();
        id = created_at.getSecond();
    }

    public ArrayList<String> getRecipientsUsernames() {
        return recipients_usernames;
    }

    public String getSenderUsername() {
        return sender_username;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return created_at;
    }
    public String getContent() {
        return content;
    }
}
