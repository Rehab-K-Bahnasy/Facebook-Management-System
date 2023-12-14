package Post;

import java.time.LocalDate;
import java.util.ArrayList;

public class Comment {

    private String username;
    private String name;
    private int comment_ID;
    private int post_ID;
    private String comment_content;
    private int reacts_counter;
    //private User [] comment_react = new User[10000];
    private LocalDate comment_created_on;
    private ArrayList<Comment> comment_reply = new ArrayList<>();

    public Comment(String username,int comment_ID,int post_ID) {
        setUsername(username);
        setPost_ID(post_ID);
//        setComment_content(content);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPost_ID() {
        return post_ID;
    }

    public void setPost_ID(int post_ID) {
        this.post_ID = post_ID;
    }


    public void setComment_ID(int comment_ID) {
        this.comment_ID = comment_ID;
    }


    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setComment_created_on(LocalDate comment_created_on) {
        this.comment_created_on = comment_created_on;
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

    public long getComment_ID() {
        return comment_ID;
    }


    public int getReacts_counter() {
        return reacts_counter;
    }


    public String getComment_content() {
        return comment_content;
    }

    public int getReacts() {
        return reacts_counter;
    }

    public void modifyReacts(boolean check_if_liked) {
        if (check_if_liked)
            reacts_counter++;
        else
            reacts_counter--;
    }



}
