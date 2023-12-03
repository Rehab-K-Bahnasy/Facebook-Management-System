module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens dataManager to javafx.fxml;
    exports dataManager;
}