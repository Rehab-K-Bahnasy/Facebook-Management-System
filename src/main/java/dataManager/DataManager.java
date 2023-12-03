package dataManager;

import javafx.scene.Scene;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TransferQueue;

public class DataManager {
    private static Map<String,String> users = new HashMap<>();
    public static void read() throws FileNotFoundException {
        try {
            File file = new File("src\\main\\resources\\dataManager\\usersData.txt");
            Scanner in = new Scanner(file);

            while (in.hasNextLine())
            {
                String line = in.nextLine();
                System.out.println(line);
                String[] userData = line.split(" ");
            }
            in.close();

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }

    }
    public static void write()
    {
        //first open the file and write nothing to empty it
        //then write the whole new data
        try {
            File file = new File("src\\main\\resources\\dataManager\\usersData.txt");
            FileWriter out = new FileWriter(file);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        read();
    }
    public static void isValidUserCredentials(String email,String Password)
    {
        //check if email
//        if(!users.containsKey(email))
//        {
//            return false;
//        }
//
//        return true;
    }
    //method returns user with specific email

}