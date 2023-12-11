package conversation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Message {
    private byte id;
    final private LocalDate date = LocalDate.now();
    private String content;
    private final LocalDate created_at = LocalDate.now();

    public Message(byte id, String content) {
        this.id = id;
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    boolean checkID(byte id) {
        return (id == this.id);
    }
    public String getContent() {
        return content;
    }
}
