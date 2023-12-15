package Post;

import dataManager.DataManager;
import user.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Comment implements Serializable {
    private static int cnt = 0;
    private final User creator;
    private int comment_ID;
    private String comment_content;
    private LocalDate comment_created_on;
    private ArrayList<Comment> comment_reply;
    private ArrayList<User> reactors;

    public Comment(String content) {
        creator = DataManager.getCurrentUser();
        setComment_content(content);
        setComment_ID(cnt);
        comment_created_on = LocalDate.now();
        comment_reply = new ArrayList<>();
        reactors = new ArrayList<>();
        cnt++;
    }

    public String getUsername() {
        return creator.getUsername();
    }

    public String getName() {
        return creator.getName();
    }

    public void setComment_ID(int comment_ID) {
        this.comment_ID = comment_ID;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setComment_reply(ArrayList<Comment> comment_reply) {
        this.comment_reply = comment_reply;
    }

    public ArrayList<Comment> getComment_reply() {
        return comment_reply;
    }

    public LocalDate getComment_created_on() {
        return comment_created_on;
    }

    public int getReactsCounter() {
        return reactors.size();
    }

    public String getComment_content() {
        return comment_content;
    }

    public void addReact(User reactor) {
        reactors.add(reactor);
    }

    public void removeReact(User reactor) {
        reactors.remove(reactor);
    }

    public boolean hasUserLikedComment(User user) {
        return reactors.contains(user);
    }

    public int getRepliesCounter() {
        return comment_reply.size();
    }

    public void addReply(Comment comment) {
        comment_reply.add(comment);
    }
}
