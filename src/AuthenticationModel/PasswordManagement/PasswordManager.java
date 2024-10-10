package AuthenticationModel.PasswordManagement;

import AuthenticationModel.AccountManagement.Account;
import AuthenticationModel.AccountManagement.AccountManager;
import AuthenticationModel.AuthenticationManager;
import Util.ExceptionUtil.UserNotFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.function.BiFunction;

public class PasswordManager {

    public static boolean validateAccountCredentials(String username , String password , String confirmPassword , BiFunction<String,String,Boolean> passwordValidator) {
           if(!username.contains("@") || !username.endsWith(".com")) {
               AuthenticationManager.printErrorMessage("Invalid username ");
               return false;
           }
           else
               return passwordValidator.apply(password,confirmPassword);
    }

    public static boolean validatePassword(String password , String confirmPassword)
    {
        return validatePasswordMatch(password,confirmPassword) && validatePasswordStrength(password);
    }

    public static boolean validatePasswordMatch(String password , String confirmPassword)
    {
        if(!password.equals(confirmPassword)) {
            AuthenticationManager.printErrorMessage("Passwords doesn't match");
            return false;
        }
        else return true;
    }

    public static boolean validatePasswordStrength(String password)
    {
        if(password.length() < 8) {
            AuthenticationManager.printErrorMessage("Password must be atleast 8 characters long");
            return false;
        }
        else if(!password.matches(".*[A-Z].*"))
        {
            AuthenticationManager.printErrorMessage("Password must contain atleast one uppercase letter");
            return false;
        }
        else if(!password.matches(".*[a-z].*")) {
            AuthenticationManager.printErrorMessage("Password must contain atleast one lowercase letter");
            return false;
        }
        else if(!password.matches(".*\\d.*")) {
            AuthenticationManager.printErrorMessage("Password must contain atleast one number");
            return false;
        }
        else if(!password.matches(".*[!@#$%^&*()-+=].*"))
        {
            AuthenticationManager.printErrorMessage("Password must contain atleast one special character");
            return false;
        }
        else{
            return true;
        }
    }

    public static String generatePassword()
    {
         String password = PasswordGenerator.generateRandomPassword();
         if(password != null)
         {
             System.out.println("Password Generated Successfully");
             return password;
         }
         else {
             System.out.println("Password Generation Failed !!");
             return null;
         }

    }

    public static void changePassword(String username , String oldPassword , String newPassword) throws UserNotFoundException
    {
        Account userAccount = null;
        try {
           userAccount =  AccountManager.accountMapWithUser.get(username);
            if(userAccount!= null && checkWithHashedPassword(newPassword,userAccount.getPassword())) {
                userAccount.setPassword(encryptPassword(newPassword));
                System.out.println("Password Reset Successfully");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void changePassword(long mobile, String newPassword)
    {
        Account userAccount = null;
        try {
            userAccount = AccountManager.accountMapWithMobile.get(mobile);
            if (userAccount != null && checkWithHashedPassword(newPassword,userAccount.getPassword())) {
                userAccount.setPassword(encryptPassword(newPassword));
                System.out.println("Password Reset Successfully");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String encryptPassword(String password)
    {

        return CryptoManager.encryptPasswordUsingHash(password);
    }

    public static boolean checkWithHashedPassword(String plainPassword , String hashedPassword)
    {
        return CryptoManager.checkPassword(plainPassword,hashedPassword);
    }


    public enum PasswordResetMethod{
        OldPassword,
        Mobile
    }

}
