package dataManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The DataWriter class is responsible for writing user data to a serialized file in the Welcome Login application.
 * It extends the DataManager class to inherit user data management functionality.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class DataWriter extends DataManager {

    /**
     * The file path for the serialized user data file.
     */
    private final static String USERS_FILE_PATH = "src\\main\\resources\\dataManager\\users.ser";

    /**
     * Writes the list of registered users to a serialized file.
     * The file path is specified by the constant USERS_FILE_PATH.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void writeUsersData() throws IOException {
        FileOutputStream file = new FileOutputStream(USERS_FILE_PATH);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(users);
        out.close();
    }
}
