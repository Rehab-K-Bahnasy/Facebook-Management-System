module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires JSON;


    opens welcomeLogin to javafx.fxml;
    exports welcomeLogin;
    opens createAccount to javafx.fxml;
}