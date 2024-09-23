package dataManager;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataWriter extends DataManager {
    private final static String USERS_FILE_PATH = "src\\main\\resources\\dataManager\\users.ser";

    public static void writeUsersData() throws IOException {
        FileOutputStream file = new FileOutputStream(USERS_FILE_PATH);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(users);
        out.close();
    }
}
