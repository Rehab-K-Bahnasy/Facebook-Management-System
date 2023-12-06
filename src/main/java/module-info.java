module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens Post to javafx.fxml;
    exports Post;
}