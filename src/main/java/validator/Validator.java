package validator;

import user.User;
import welcomeLogin.CreateAccountController;
import java.lang.String;
import java.util.Arrays;
import java.time.LocalDate;

public class Validator {
    /**
     * encrypt the string 
     * @param _string - the string you want to encrypt
     * @param key - the special key for the user
    */
    private static void transformString(char[] _string, short key) {
        for (int i = 0; i < _string.length; i++) {
            _string[i] *= key;
        }
    }

    /**
     * encrypt the string
     * @param _string - the string you want to encrypt
     * @param key - the special key for the user
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
    * @param password - the string you want to check
    * @return the password is right or not
    */
    public static boolean checkPassword(char[] actual_password, char[] password, short key) {
        transformString(password, key);
        return Arrays.equals(actual_password, password);
    }
    public static boolean checkUsernameValidation(){
        String username = CreateAccountController.getUsername();
        for (int i = 0; i < username.length();i++) {
            char x = username.charAt(i);
            if( x >= '0' && x <= '9' )
            {
                return false;
            }
        }
        return true;
    }
    public static boolean checkPhoneNumberValidation(){
        String phone_number = CreateAccountController.getPhoneNumber();
        for (int i = 0; i < phone_number.length();i++) {
            char x = phone_number.charAt(i);
            if( x < '0' || x > '9' ) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkEmailValidation(){
        String Email = CreateAccountController.getEmail();
        for (int i = 0; i < Email.length();i++) {
            char x = Email.charAt(i);
            if( x == '@' ) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPasswordVerification(){
        return CreateAccountController.getPassword().equals(CreateAccountController.getConfirmPassword());
    }
    /**
     * checks if the user exists
     * @param user - the user you want to check
     * @param users - the entire Users database
     * @return the user is used or not
     */
    public static boolean checkUser(String identitiy, User[] users) {
        for (User user_check : users) {
            if (user_check.hasMatchingIdentity(identitiy)) return true;
        }
        return false;
    }
    
    /**
     * checks if the username exists
     * @param username - the username you want to use
     * @param users - the entire Users database
     * @return the username is used or not
     */
    public static boolean checkUsername(String username, User[] users) {
        boolean found = true;
        for (User user : users) {
            if (!user.checkUsernameMatch(username)){
                found = false;
                break;
            }
        }
        return found;
    }

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

    /**
     * checks if the person is adult or not
     * @param birth_date - the birth-date of the person
     * @return the person is adult or not
     */
    public static boolean checkAdult() {
        if (!CreateAccountController.getBirthdate().isAfter(LocalDate.now().minusYears(18))) {
            return false;
        }
        return true;
    }
}
