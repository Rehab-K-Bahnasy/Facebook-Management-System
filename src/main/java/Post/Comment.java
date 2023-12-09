package Post;

import java.time.LocalDate;
import java.util.ArrayList;

public class Comment {
    private String username;
    private int comment_ID;
    private int post_ID;
    private String comment_content;
    //private User [] comment_react = new User[10000];
    private LocalDate comment_created_on;
    private ArrayList<Comment> comment_reply = new ArrayList<>();

    public Comment(String username,int comment_ID,int post_ID) {
        setUsername(username);
        setComment_ID(comment_ID);
        setPost_ID(post_ID);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getComment_content() {
        return comment_content;
    }

    void addComment(Comment comment) {
        this.comment_content = comment.comment_content;
        this.username = comment.username;
        this.comment_created_on = comment.comment_created_on;
        comment_ID++;

    }

//   public void switchtopostscene(ActionEvent event) throws IOException {
//        FXMLLoader loader =  new FXMLLoader(Objects.requireNonNull(getClass().getResource("PostScene.fxml")));
//        root=loader.load();
//        Post post=loader.getController();
//        post.setPost_caption("EZZZZZZZutfiyvhZZZZZZZZZZZZZZZZZZZ");
//        post.setPost_privacy("Public");
//        post.displaycaption(post);
//        post.displayprivacy(post);
//        post.displaycounter(post);
//        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        post_scene=new Scene(root);
//        stage.setScene(post_scene);
//        stage.show();
//    }


}
