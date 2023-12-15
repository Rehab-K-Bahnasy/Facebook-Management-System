package Post;

import user.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Post implements Serializable {
    private static int cnt = 0;
    private final User creator;
    private int post_ID;
    private final LocalDate post_created_on;
    private String post_caption;
    private String privacy;
    private ArrayList<Comment> comments_on_post;
    private ArrayList<User> tagged_users;
    private ArrayList<User> reactors;

    public Post(User creator, String post_caption, String post_privacy) {
        this.creator = creator;
        setCaption(post_caption);
        setPrivacy(post_privacy);
        comments_on_post = new ArrayList<>();
        reactors = new ArrayList<>();
        tagged_users = new ArrayList<>();
        post_created_on = LocalDate.now();
        setID(cnt);
        cnt++;
    }

    public Post(User creator, String post_caption, String post_privacy, ArrayList<User> tagged_users) {
        this(creator, post_caption, post_privacy);
        setTaggedUsers(tagged_users);
    }

    public String getCreatorName() {
        return creator.getName();
    }

    public String getCreatorUsername() {
        return creator.getUsername();
    }

    public int getID() {
        return post_ID;
    }

    public void setID(int post_ID) {
        this.post_ID = post_ID;
    }

    public LocalDate getCreatedOn() {
        return post_created_on;
    }

    public String getCaption() {
        return post_caption;
    }

    public void setCaption(String post_caption) {
        this.post_caption = post_caption;
    }

    public int getReacts() {
        return reactors.size();
    }


    public ArrayList<Comment> getComments_on_post() {
        return comments_on_post;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String post_privacy) {
        this.privacy = post_privacy;
    }

    public void addComment(Comment comment) {
        comments_on_post.add(comment);
    }
    public int getCommentsCounter() {
        return comments_on_post.size();
    }

    public void setTaggedUsers(ArrayList<User> tagged_users) {
        this.tagged_users = tagged_users;
    }

    public void addReact(User reactor) {
        reactors.add(reactor);
    }

    public void removeReact(User reactor) {
        reactors.remove(reactor);
    }

    public boolean hasUserLikedPost(User user) {
        return reactors.contains(user);
    }

    public ArrayList<User> getTaggedUsers() {
        return this.tagged_users;
    }
}