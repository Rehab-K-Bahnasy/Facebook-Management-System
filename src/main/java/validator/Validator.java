package validator;

import dataManager.DataManager;
import user.User;
import welcomeLogin.CreateAccountController;

import java.time.LocalDate;

/**
 * The Validator class provides methods for validating various user inputs in the Welcome Login application.
 * It includes methods for name validation, phone number format, email format, password verification, login validation,
 * checking email uniqueness, checking unique username, checking unique phone number, and checking if the user is an adult.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class Validator extends DataManager {

    /**
     * Checks if the provided name is valid.
     *
     * @param name The name to be validated.
     * @return True if the name is valid, false otherwise.
     */
    public static boolean checkNameValidation(String name) {
        String nameRegex = "^[a-zA-Z\\s\\-']+$";
        return name.matches(nameRegex);
    }

    /**
     * Checks if the provided phone number has a valid format.
     *
     * @param phone_number The phone number to be validated.
     * @return True if the phone number has a valid format, false otherwise.
     */
    public static boolean checkPhoneNumberFormat(String phone_number) {
        String regex_phone_number = "^(\\+\\d{1,3}[- ]?)?\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$";
        return phone_number.matches(regex_phone_number);
    }

    /**
     * Checks if the provided email has a valid format.
     *
     * @param email The email to be validated.
     * @return True if the email has a valid format, false otherwise.
     */
    public static boolean checkEmailFormat(String email) {
        String regex_mail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex_mail);
    }

    /**
     * Checks if the provided password and confirmation password match and are not empty.
     *
     * @param password              The password to be verified.
     * @param confirmation_password The confirmation password to be verified.
     * @return True if the passwords match and are not empty, false otherwise.
     */
    public static boolean checkPasswordVerification(String password, String confirmation_password) {
        return !(password.isEmpty()) && !(confirmation_password.isEmpty()) && password.equals(confirmation_password);
    }

    /**
     * Checks if the provided identity and password match a registered user.
     *
     * @param identity The username or email entered by the user.
     * @param password The password entered by the user.
     * @return True if the identity and password match a registered user, false otherwise.
     */
    public static boolean checkLoginValidation(String identity, String password) {
        if (DataManager.retrieveUser(identity) == null)
            return false;

        return DataManager.retrieveUser(identity).hasPasswordMatch(password);
    }

    /**
     * Checks if the provided email is in a valid format and is unique among registered users.
     *
     * @param email The email to be checked.
     * @return 0 if the email is valid and unique, 1 if the email format is invalid, 2 if the email is not unique.
     */
    public static int checkEmail(String email) {
        if (!checkEmailFormat(email))
            return 1;

        for (User user : users) {
            if (user.hasEmailMatch(email))
                return 2;
        }
        return 0;
    }

    /**
     * Checks if the provided username is unique among registered users.
     *
     * @param username The username to be checked.
     * @return True if the username is unique, false otherwise.
     */
    public static boolean checkUniqueUsername(String username) {
        for (User user : users) {
            if (user.hasUsernameMatch(username))
                return false;
        }
        return !(username.isEmpty());
    }

    /**
     * Checks if the provided phone number is in a valid format and is unique among registered users.
     *
     * @param phone_number The phone number to be checked.
     * @return 0 if the phone number is valid and unique, 1 if the phone number format is invalid, 2 if the phone number is not unique.
     */
    public static int checkPhone(String phone_number) {
        if (!checkPhoneNumberFormat(phone_number))
            return 1;
        for (User user : users) {
            if (user.hasPhoneNumberMatch(phone_number))
                return 2;
        }
        return 0;
    }

    /**
     * Checks if the provided date corresponds to an adult user (more than 13 years old).
     *
     * @param date The date of birth to be checked.
     * @return True if the user is an adult, false otherwise.
     */
    public static boolean checkAdult(LocalDate date) {
        if (!date.isAfter(LocalDate.now().minusYears(13))) {
            return true;
        }
        return false;
    }
}
