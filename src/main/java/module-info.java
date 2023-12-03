module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens welcomeLogin to javafx.fxml;
    exports welcomeLogin;
}