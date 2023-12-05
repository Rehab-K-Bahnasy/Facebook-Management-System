package dataManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import user.User;

import java.io.FileReader;
import java.io.IOException;

public class DataReader extends DataManager {
    public static void readUsersData() {
        //to parse the users.json file to an array
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(USERS_FILE_PATH)) {
            //read json file and parse it to a json array
            JSONArray jsonUsers = (JSONArray) jsonParser.parse(reader);
            createUsersFromJSON(jsonUsers);
        } catch (IOException | ParseException | NullPointerException exp) {
            System.out.println(exp.toString());
        }
    }

    private static void createUsersFromJSON(JSONArray jsonUsers) {
        for (Object obj : jsonUsers) {
            try {
                JSONObject jsonUser = (JSONObject) obj;
                users.add(new User(jsonUser));
            } catch (ClassCastException | NullPointerException exp) {
                System.out.println(exp.toString());
            }
        }
    }
}
