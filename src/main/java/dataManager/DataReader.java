package dataManager;

import user.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DataReader extends DataManager {
    private final static String USERS_FILE_PATH = "src\\main\\resources\\dataManager\\users.ser";

    public static void readUsersData() {
        try {
            FileInputStream file = new FileInputStream(USERS_FILE_PATH);
            ObjectInputStream in = new ObjectInputStream(file);
            users = (ArrayList<User>) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException ex){
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

}
