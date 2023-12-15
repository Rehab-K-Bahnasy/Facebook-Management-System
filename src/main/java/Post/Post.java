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
    private int reacts_counter;
    private String privacy;
    private ArrayList<Comment> comments_on_post;
    private ArrayList<User> tagged_users;

    public Post(User creator, String post_caption, String post_privacy) {
        this.creator = creator;
        setCaption(post_caption);
        setPrivacy(post_privacy);
        comments_on_post = new ArrayList<>();
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

    public String getCreatedOn() {
        return post_created_on.toString();
    }

    public String getCaption() {
        return post_caption;
    }

    public void setCaption(String post_caption) {
        this.post_caption = post_caption;
    }

    public int getReacts() {
        return reacts_counter;
    }

    public void addComment(Comment comment) {
        comments_on_post.add(comment);
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

    public int getCommentsCounter() {
        return comments_on_post.size();
    }

    public void modifyReacts(boolean check_if_liked) {
        if (check_if_liked)
            reacts_counter++;
        else
            reacts_counter--;
    }

    public ArrayList<User> getTaggedUsers() {
        return this.tagged_users;
    }

    public void setTaggedUsers(ArrayList<User> tagged_users) {
        this.tagged_users = tagged_users;
    }
}