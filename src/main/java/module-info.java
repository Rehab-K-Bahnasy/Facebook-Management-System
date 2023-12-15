module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens welcomeLogin to javafx.fxml;
    opens userDashaboard to javafx.fxml;
    opens Post to javafx.fxml;
    opens user to javafx.fxml;
    exports welcomeLogin;
    exports userDashaboard;
    exports Post;
    exports user;


}