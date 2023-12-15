package dataManager;

import user.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * The DataReader class is responsible for reading user data from a serialized file in the Welcome Login application.
 * It extends the DataManager class to inherit user data management functionality.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class DataReader extends DataManager {

    /**
     * The file path for the serialized user data file.
     */
    private final static String USERS_FILE_PATH = "src\\main\\resources\\dataManager\\users.ser";

    /**
     * Reads user data from the serialized file and populates the list of registered users.
     * The file path is specified by the constant USERS_FILE_PATH.
     */
    public static void readUsersData() {
        try {
            FileInputStream file = new FileInputStream(USERS_FILE_PATH);
            ObjectInputStream in = new ObjectInputStream(file);
            users = (ArrayList<User>) in.readObject();
            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}
