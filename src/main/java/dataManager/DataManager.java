package dataManager;

import user.User;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataManager
{
    private static Map<String,User> users = new HashMap<>();
    public static void readUsers()
    {
        try
        {
            File file = new File("src\\main\\resources\\dataManager\\usersData.txt");
            Scanner in = new Scanner(file);

            //userData structure: email password firstName lastName phoneNumber gender
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
}