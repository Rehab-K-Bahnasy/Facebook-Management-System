package user;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
    final private String content;
    final private String sender_username;
    final private ArrayList<String> recipients;
    private final LocalDateTime created_at;

    public Message(String content, String sender_username, ArrayList<String> recipients) {
        this.content = content;
        this.sender_username = sender_username;
        this.recipients = recipients;
        this.created_at = LocalDateTime.now();
    }

    public ArrayList<String> getRecipientsId() {
        return recipients;
    }

    public String getSenderId() {
        return sender_username;
    }

    public LocalDateTime getDate() {
        return created_at;
    }

    public String getContent() {
        return content;
    }
}
