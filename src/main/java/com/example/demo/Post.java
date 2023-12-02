import java.time.LocalDate;
import java.util.ArrayList;
public class Post {
    private int post_Id;

    private LocalDate for_post=LocalDate.now();
    private char [] caption = new char[4000];

    private int reacts;

    private ArrayList<Comment> comments = new ArrayList<Comment>();

    private char[] privacy;

    private User [] users_tagged = new User[4000];


    public void setPost_Id(int post_Id) {
        this.post_Id = post_Id;
    }

    public void setCaption(char[] caption) {
        this.caption = caption;
    }

    public void setReacts(int reacts) {
        this.reacts = reacts;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void setPrivacy(char[] privacy) {
        this.privacy = privacy;
    }

    public void setUsers_tagged(User[] users_tagged) {
        this.users_tagged = users_tagged;
    }

    public int getPost_Id() {
        return post_Id;
    }

    public char[] getCaption() {
        return caption;
    }

    public int getReacts() {
        return reacts;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public User[] getUsers_tagged() {
        return users_tagged;
    }

    public char[] getPrivacy() {
        return privacy;
    }

    public LocalDate getFor_post() {
        return for_post;
    }
}
