package validator;

import dataManager.DataManager;
import user.User;
import welcomeLogin.CreateAccountController;

import java.lang.String;
import java.util.Arrays;
import java.time.LocalDate;

public class Validator extends DataManager {
    /**
     * encrypt the string
     *
     * @param _string - the string you want to encrypt
     * @param key     - the special key for the user
     */
    private static void transformString(char[] _string, short key) {
        for (int i = 0; i < _string.length; i++) {
            _string[i] *= key;
        }
    }

    /**
     * encrypt the string
     *
     * @param _string - the string you want to encrypt
     * @param key     - the special key for the user
     * @return encrypted string
     */
    private static char[] returnTransformedString(char[] _string, short key) {
        for (int i = 0; i < _string.length; i++) {
            _string[i] *= key;
        }
        return _string;
    }

    /**
     * @param actual_password - the real password
     * @param password        - the string you want to check
     * @return the password is right or not
     */
    public static boolean checkPassword(char[] actual_password, char[] password, short key) {
        transformString(password, key);
        return Arrays.equals(actual_password, password);
    }

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

    public static boolean genderCheck(boolean male, boolean female) {
        return male || female;
    }

    /**
     * checks if the user exists
     * @param identity - the string you want to check
     * @return the user is used or not
     */
    public static boolean checkUser(String identity) {
        for (User user_check : users) {
            if (user_check.hasMatchingIdentity(identity))
                return false;
        }
        return true;
    }
    public static int checkEmail(String email) {
        if (!checkEmailFormat(email))
            return 1;

        for (User user : users) {
            if (user.hasMatchingIdentity(email))
                return 2;
        }
        return 0;
    }
    public static boolean checkUniqueUsername(String username) {
        for (User user : users) {
            if (user.hasMatchingIdentity(username))
                return false;
        }
        return !(username.isEmpty());
    }
    public static int checkPhone(String phone_number) {
        if(!checkPhoneNumberFormat(phone_number))
            return 1;
        for (User user : users) {
            if (user.hasMatchingIdentity(phone_number))
                return 2;
        }
        return 0;
    }
    /**
     * checks if the person is adult or not
     *
     * @param date - the birth-date of the person
     * @return the person is adult or not
     */
    public static boolean checkAdult(LocalDate date) {
        if (!date.isAfter(LocalDate.now().minusYears(18))) {
            return true;
        }
        return false;
    }
}
//    /**
//     * checks if the username exists
//     * @param username - the username you want to use
//     * @param users - the entire Users database
//     * @return the username is used or not
//     */
//    public static boolean checkUsername(String username, User[] users) {
//        boolean found = true;
//        for (User user : users) {
//            if (!user.checkUsernameMatch(username)){
//                found = false;
//                break;
//            }
//        }
//        return found;
//    }

//    /**
//     * checks if the phone exists
//     * @param phone - the phone you want to use
//     * @param users - the entire Users database
//     * @return the phone is used or not
//     */
//    public static boolean checkPhone(char[] phone, User[] users) {
//        boolean found = true;
//        for (User user : users) {
//            if (!Arrays.equals(user.getPhone, phone)) {
//                found = false;
//                break;
//            }
//        }
//        return found;
//    }

