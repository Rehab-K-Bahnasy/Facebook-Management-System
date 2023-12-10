package Post;

import java.time.LocalDate;
import java.util.ArrayList;

public class Post {
    private String username;
    private int post_ID;
    private LocalDate post_created_on;
    private String post_caption;
    private int reacts_counter;

    //private User [] users_tagged_in_post = new User[10000];
    private ArrayList<Comment> comments_on_post = new ArrayList<>();
    private String privacy;

    Post(int post_ID, String post_caption, LocalDate post_created_on, String post_privacy) {
        setID(post_ID);
        setCaption(post_caption);
        setCreatedOn(post_created_on);
        setPrivacy(post_privacy);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getID() {
        return post_ID;
    }

    public void setID(int post_ID) {
        this.post_ID = post_ID;
    }

    public String getCreatedOn() {
        return post_created_on.toString();
    }

    public void setCreatedOn(LocalDate post_created_on) {
        this.post_created_on = post_created_on;
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

    public void setComments(ArrayList<Comment> comments_on_post) {
        this.comments_on_post = comments_on_post;
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
}
