module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens welcomeLogin to javafx.fxml;
    opens userDashaboard to javafx.fxml;
    exports welcomeLogin;
    exports userDashaboard;
}