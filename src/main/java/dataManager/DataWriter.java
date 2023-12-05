package dataManager;

import org.json.simple.JSONArray;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter extends DataManager {
    private static void writeJSONArrayToFile(JSONArray jsonArray, FileWriter writer) throws IOException {
        writer.write("[\n");

        for (var user : jsonArray) {
            writer.write("\t");
            writer.write(user.toString());
            if (user != jsonArray.getLast())
                writer.write(" ,");
            writer.write("\n");
        }
        writer.write("]");
    }

    public static void writeUsersData() {
        JSONArray jsonUsers = new JSONArray();
        for (var user : users) {
            jsonUsers.add(user.toJsonObject());
        }

        try (FileWriter writer = new FileWriter(USERS_FILE_PATH)) {
            writeJSONArrayToFile(jsonUsers, writer);
        } catch (IOException exp) {
            System.out.println(exp);
        }
    }
}
