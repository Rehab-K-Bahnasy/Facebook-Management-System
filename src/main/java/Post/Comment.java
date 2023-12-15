package Post;

import dataManager.DataManager;
import user.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The {@code Comment} class represents a user comment on a post.
 * Each comment has a unique ID, content, creation date, and can be replied to by other comments.
 * Users can react to comments, and comments can also have replies.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class Comment implements Serializable {

    /** The counter for generating unique comment IDs. */
    private static int cnt = 0;

    /** The user who created the comment. */
    private final User creator;

    /** The unique identifier for the comment. */
    private int comment_ID;

    /** The content of the comment. */
    private String comment_content;

    /** The date and time when the comment was created. */
    private LocalDate comment_created_on;

    /** The list of replies to this comment. */
    private ArrayList<Comment> comment_reply;

    /** The list of users who reacted to this comment. */
    private ArrayList<User> reactors;

    /**
     * Creates a new {@code Comment} with the specified content.
     *
     * @param content The content of the comment.
     */
    public Comment(String content) {
        creator = DataManager.getCurrentUser();
        setComment_content(content);
        setComment_ID(cnt);
        comment_created_on = LocalDate.now();
        comment_reply = new ArrayList<>();
        reactors = new ArrayList<>();
        cnt++;
    }

    /**
     * Gets the username of the user who created the comment.
     *
     * @return The username of the comment creator.
     */
    public String getUsername() {
        return creator.getUsername();
    }

    /**
     * Gets the name of the user who created the comment.
     *
     * @return The name of the comment creator.
     */
    public String getName() {
        return creator.getName();
    }

    /**
     * Sets the unique identifier for the comment.
     *
     * @param comment_ID The comment ID to set.
     */
    public void setComment_ID(int comment_ID) {
        this.comment_ID = comment_ID;
    }

    /**
     * Sets the content of the comment.
     *
     * @param comment_content The content to set for the comment.
     */
    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    /**
     * Sets the list of replies to this comment.
     *
     * @param comment_reply The list of replies to set.
     */
    public void setComment_reply(ArrayList<Comment> comment_reply) {
        this.comment_reply = comment_reply;
    }

    /**
     * Gets the list of replies to this comment.
     *
     * @return The list of replies to this comment.
     */
    public ArrayList<Comment> getComment_reply() {
        return comment_reply;
    }

    /**
     * Gets the creation date of the comment.
     *
     * @return The creation date of the comment.
     */
    public LocalDate getComment_created_on() {
        return comment_created_on;
    }

    /**
     * Gets the number of reactions (likes) on the comment.
     *
     * @return The number of reactions on the comment.
     */
    public int getReactsCounter() {
        return reactors.size();
    }

    /**
     * Gets the content of the comment.
     *
     * @return The content of the comment.
     */
    public String getComment_content() {
        return comment_content;
    }

    /**
     * Adds a user reaction to the comment.
     *
     * @param reactor The user who reacted to the comment.
     */
    public void addReact(User reactor) {
        reactors.add(reactor);
    }

    /**
     * Removes a user reaction from the comment.
     *
     * @param reactor The user whose reaction is to be removed.
     */
    public void removeReact(User reactor) {
        reactors.remove(reactor);
    }

    /**
     * Checks if a user has liked the comment.
     *
     * @param user The user to check for liking the comment.
     * @return {@code true} if the user has liked the comment, {@code false} otherwise.
     */
    public boolean hasUserLikedComment(User user) {
        return reactors.contains(user);
    }

    /**
     * Gets the number of replies to this comment.
     *
     * @return The number of replies to this comment.
     */
    public int getRepliesCounter() {
        return comment_reply.size();
    }

    /**
     * Adds a reply to this comment.
     *
     * @param comment The comment to add as a reply.
     */
    public void addReply(Comment comment) {
        comment_reply.add(comment);
    }
}