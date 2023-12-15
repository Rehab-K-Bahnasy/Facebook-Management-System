package validator;

import dataManager.DataManager;
import user.User;
import welcomeLogin.CreateAccountController;

import java.lang.String;
import java.util.Arrays;
import java.time.LocalDate;

public class Validator extends DataManager {

    public static boolean checkNameValidation(String name) {
        String nameRegex = "^[a-zA-Z\\s\\-']+$";
        return name.matches(nameRegex);

    }

    public static boolean checkPhoneNumberFormat(String phone_number) {
        String regex_phone_number = "^(\\+\\d{1,3}[- ]?)?\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$";
        return phone_number.matches(regex_phone_number);
    }

    public static boolean checkEmailFormat(String Email) {
        String regex_mail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Email.matches(regex_mail);
    }

    public static boolean checkPasswordVerification(String password, String confirmation_password) {
        return !(password.isEmpty()) && !(confirmation_password.isEmpty()) && password.equals(confirmation_password);
    }


    public static boolean checkLoginValidation(String identity , String password ) {
        if(DataManager.retrieveUser(identity)==null)
            return false;

           return DataManager.retrieveUser(identity).hasPasswordMatch(password);
    }
    public static int checkEmail(String email) {
        if (!checkEmailFormat(email))
            return 1;

        for (User user : users) {
            if (user.hasEmailMatch(email))
                return 2;
        }
        return 0;
    }
    public static boolean checkUniqueUsername(String username) {
        for (User user : users) {
            if (user.hasUsernameMatch(username))
                return false;
        }
        return !(username.isEmpty());
    }
    public static int checkPhone(String phone_number) {
        if(!checkPhoneNumberFormat(phone_number))
            return 1;
        for (User user : users) {
            if (user.hasPhoneNumberMatch(phone_number))
                return 2;
        }
        return 0;
    }

    public static boolean checkAdult(LocalDate date) {
        if (!date.isAfter(LocalDate.now().minusYears(18))) {
            return true;
        }
        return false;
    }
}

