package Login; // Only include this line if you're keeping this class in the "Login" package

/**
 * This class handles validation logic for user registration.
 * 
 * @author lab_services_student
 */
public class LoginBackend {

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        // At least 8 characters, 1 capital letter, 1 digit, and 1 special character
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=]).{8,}$");
    }

    public boolean checkCellPhoneNumber(String cell) {
        // Matches +27 followed by exactly 9 digits (South African format)
        return cell.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username, String password, String cell) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "Registration successful.";
    }
}
