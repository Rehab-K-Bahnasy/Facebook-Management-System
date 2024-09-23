module com.example.welcomelogin {
    requires javafx.controls;
    requires javafx.fxml;

    opens welcomeLogin to javafx.fxml;
    opens userDashboard to javafx.fxml;
    opens Post to javafx.fxml;
    opens user to javafx.fxml;
    opens conversation to javafx.fxml;
    exports conversation;
    exports welcomeLogin;
    exports userDashboard;
    exports Post;
    exports user;


}
