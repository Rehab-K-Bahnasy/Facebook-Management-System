package conversation;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Message class represents a message in the Welcome Login application.
 * It includes information such as content, sender's username, recipients' usernames,
 * creation date, and a unique identifier (ID) for the message.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class Message {

    private int id;
    private final String content;
    private final String sender_username;
    private final ArrayList<String> recipients_usernames;
    private LocalDateTime created_at;

    /**
     * Constructs a Message object with the specified content, sender's username, and recipients' usernames.
     *
     * @param content              The content of the message.
     * @param sender_username      The username of the sender.
     * @param recipients_usernames The usernames of the message recipients.
     */
    public Message(String content, String sender_username, ArrayList<String> recipients_usernames) {
        this.content = content;
        this.sender_username = sender_username;
        this.recipients_usernames = recipients_usernames;
        created_at = LocalDateTime.now();
        id = created_at.getSecond();
    }

    /**
     * Retrieves the usernames of the recipients of the message.
     *
     * @return The usernames of the message recipients.
     */
    public ArrayList<String> getRecipientsUsernames() {
        return recipients_usernames;
    }

    /**
     * Retrieves the username of the sender of the message.
     *
     * @return The username of the sender.
     */
    public String getSenderUsername() {
        return sender_username;
    }

    /**
     * Retrieves the unique identifier (ID) of the message.
     *
     * @return The unique identifier of the message.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the creation date and time of the message.
     *
     * @return The creation date and time of the message.
     */
    public LocalDateTime getDate() {
        return created_at;
    }

    /**
     * Retrieves the content of the message.
     *
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }
}
