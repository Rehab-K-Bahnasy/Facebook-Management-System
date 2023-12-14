package conversation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
    final private String content;
    final private String sender_username;
    final private ArrayList<String> recipients_usernames;
    private final LocalDateTime created_at = LocalDateTime.now();

    public Message(String content, String sender_username, ArrayList<String> recipients_usernames) {
        this.content = content;
        this.sender_username = sender_username;
        this.recipients_usernames = recipients_usernames;
    }

    public ArrayList<String> getRecipientsUsernames() {
        return recipients_usernames;
    }

    public String getSenderUsername() {
        return sender_username;
    }

    public LocalDateTime getDate() {
        return created_at;
    }
    public String getContent() {
        return content;
    }
}
