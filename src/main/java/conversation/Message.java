package conversation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
    final private String content;
    final private byte sender_id;
    final private ArrayList<Byte> recipients_id;
    private final LocalDateTime created_at = LocalDateTime.now();

    public Message(String content, byte sender_id, ArrayList<Byte> recipients_id) {
        this.content = content;
        this.sender_id = sender_id;
        this.recipients_id = recipients_id;
    }

    public ArrayList<Byte> getRecipientsId() {
        return recipients_id;
    }

    public byte getSenderId() {
        return sender_id;
    }

    public LocalDateTime getDate() {
        return created_at;
    }
    public String getContent() {
        return content;
    }
}
