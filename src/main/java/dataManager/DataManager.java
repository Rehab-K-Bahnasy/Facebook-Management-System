package dataManager;

import user.User;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataManager
{
    //key:username,value:user
    protected static Map<String,User> users = new HashMap<>();
    private static final String usersFilePath = "src\\main\\resources\\dataManager\\usersData.txt";
    public static void readUsers()
    {
        try
        {
            File file = new File(usersFilePath);
            Scanner in = new Scanner(file);

            //userData structure: username email password firstName lastName phoneNumber birthDate gender
            while (in.hasNextLine())
            {
                String line = in.nextLine();
                System.out.println(line);
                String[] userData = line.split(" ");
                users.put(userData[0],new User(userData));
            }
            in.close();
        }
        catch (Exception exp)
        {
            System.out.println(exp.getMessage());
        }
    }
    public static void writeUsers()
    {
        //first open the file and write nothing to empty it
        //then write the whole new data
        try
        {
            File file = new File("src\\main\\resources\\dataManager\\usersData.txt");
            FileWriter out = new FileWriter(file);

            out.close();
        }
        catch (Exception exp)
        {
            System.out.println(exp.getMessage());
        }
    }

    //this should be the validator, and should extend the data manger
    public static boolean isValidUserCredentials(String inputEmail,String inputPassword)
    {
        if(!users.containsKey(inputEmail))
        {
            return false;
        }
        var retrievedUser = users.get(inputEmail);
        return retrievedUser.checkPasswordMatch(inputPassword);
    }
    public static User retrieveUser(String email)
    {
        return users.get(email);
    }

    public static void main(String[] args)
    {
        JSONObject user = new JSONObject();
    }
}