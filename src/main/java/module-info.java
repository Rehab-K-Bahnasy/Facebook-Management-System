module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens Posts to javafx.fxml;
    exports Posts;
}