package AuthenticationModel;

import AuthenticationModel.AccountManagement.Account;
import AuthenticationModel.AccountManagement.AccountManager;
import AuthenticationModel.AccountManagement.BuyerAccount;
import AuthenticationModel.AccountManagement.VendorAccount;
import AuthenticationModel.PasswordManagement.PasswordManager;
import InventoryModel.ProductManagement.ProductCategories;
import Util.Category;
import Util.ExceptionUtil.UserNotFoundException;
import Util.GlobalScanner;
import Util.ICategory;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BiFunction;

import static AuthenticationModel.AccountManagement.AccountManager.*;

public class AuthenticationManager {

    public static Scanner scan = GlobalScanner.getInstance();
    public static BiFunction<String,String,Boolean> passwordValidation = PasswordManager::validatePassword;

    public static boolean login() {
        boolean isLoggedInProcessCompleted = false;
        do {
            System.out.println("Choose Login Method : 1 --> UserNamePassword || 2 -->Mobile || Press any number -->Exit");
            int loginMethod = scan.nextInt();
            switch (loginMethod) {
                case 1 -> {
                    if(LoginUsingUsernamePassword())
                       isLoggedInProcessCompleted = true;
                }
                case 2 -> {
                    if(LoginUsingMobile())
                        isLoggedInProcessCompleted = true;
                }
                default -> {
                    exit();
                    isLoggedInProcessCompleted = true;
                }
            }
        }while (!isLoggedInProcessCompleted);
        return isLoggedInProcessCompleted;
    }

    public static boolean LoginUsingUsernamePassword()
    {
        System.out.println("Enter the UserName");
        String userName = scan.next();
        System.out.println("Enter the password");
        String password = scan.next();
       Account userAccount = accountMapWithUser.get(userName);
       AccountManager.getAccount(userName);
       if(userAccount !=null)
       {
           if(PasswordManager.checkWithHashedPassword(password,userAccount.getPassword())){
               loggedInAccounts.add(userAccount);
               AuthenticationContext.getInstance().setLoginUser(userAccount);
               System.out.println("Logged In Successfully");
               return true;
           }
           else {
               System.out.println("Password is incorrect");
           }
       }
       else
           System.out.println("User not Found");
       return false;
    }

    public static boolean LoginUsingMobile()
    {
       System.out.println("Enter the registered mobile number");
        long mobile = scan.nextLong();
        Account userAccount = accountMapWithMobile.get(mobile);
        if(userAccount !=null && userAccount.getMobile() == mobile)
        {
            loggedInAccounts.add(userAccount);
            AuthenticationContext.getInstance().setLoginUser(userAccount);
            System.out.println("Logged In Successfully");
            return true;
        }
        else
            System.out.println("This mobile number is not registered");
        return false;
    }

    public static void Register()
    {
        boolean isRegistrationSuccess = false;
        do {
            System.out.println("Enter the UserName");
            String userName = scan.next();
            System.out.println("Enter the password");
            String password = scan.next();
            System.out.println(("Confirm password"));
            String confirmPassword = scan.next();
            scan.nextLine();
            System.out.println("Enter the mobile number (Optional)");
            String mobile = scan.nextLine();
            if (validateAuthentication(userName, password,confirmPassword, mobile)) { //if validation succeed
                System.out.println("Register as V --> Vendor account || B --> Buyer account ");
                String registerAccount = scan.next();
                 String encryptedPassword = PasswordManager.encryptPassword(password);  // Encrypting password
                Long mobileNo = Optional.ofNullable(mobile).filter(m->!m.isEmpty()).map(Long::parseLong).orElse(0L);
                if (registerAccount.equalsIgnoreCase("V") && registerVendor(userName , encryptedPassword , mobileNo) ) {  //Getting vendor specific details
                    isRegistrationSuccess = true;
                } else if (registerAccount.equalsIgnoreCase("B") && registerBuyer(userName , encryptedPassword , mobileNo) ) {
                    isRegistrationSuccess = true;
                } else {
                    System.out.println("Please select any one account");

                }
            } else {
                System.out.println("Please provide valid details");
            }
            exit();
        }while(!isRegistrationSuccess);
    }

    public  static boolean registerVendor(String userName , String password , Long mobile)
    {
        try {
            System.out.println("Select the category of Product which you are willing to provide us");
            ICategory<ProductCategories> category = new Category<>();
            category.viewCategory(ProductCategories.ProductCatgory.class);
            String supplyproductCategory = scan.next();
            System.out.println("Enter Vendor name");
            String vendorName = scan.next();
            Account vendorAccount = new VendorAccount(userName, password, mobile, supplyproductCategory, vendorName);
          // VendorAccount acc = (VendorAccount) vendorAccount;
          // acc.getAccount();
            accountMapWithUser.put(userName,vendorAccount);
            accountMapWithMobile.put(mobile,vendorAccount);
            AccountManager.persistAccount(vendorAccount);
            System.out.println("Registration Successfull");
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registerBuyer(String userName , String password , Long mobile)
    {
        try {
            System.out.println("Enter the role to register: Standard || Premium");
            String role =scan.next();
            BuyerAccount buyerAccount = new BuyerAccount(userName, password, mobile);
            buyerAccount.setRole(role);
            accountMapWithUser.put(userName, buyerAccount);
            accountMapWithMobile.put(mobile, buyerAccount);
            System.out.println("Registration Successfull");
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateAuthentication(String username , String password , String confirmPassword, String mobile)
    {
        if(!PasswordManager.validateAccountCredentials(username , password , confirmPassword , passwordValidation))
            return false;
        else if(!validateMobile(mobile))  //specific for india
        {
            return false;
        }
        else
            return true;
    }

    public static boolean validateMobile(String mobile)
    {
        if(!mobile.isEmpty() && mobile.length() != 10)  //specific for india
        {
        printErrorMessage("Please provide mobile number with 10 digits");
        return false;
        }
        return true;
    }

    public static void resetPassword(String passwordResetMethod) throws UserNotFoundException {
        String username = null;
        String oldPassword = null;
        String mobile = null;
        if(passwordResetMethod.equalsIgnoreCase(PasswordManager.PasswordResetMethod.OldPassword.name()))
        {
          System.out.println("Enter the Username and old Password");
          username = scan.next();
          oldPassword = scan.next();
        }
        else if(passwordResetMethod.equalsIgnoreCase(PasswordManager.PasswordResetMethod.Mobile.name()))
        {
           System.out.println("Enter the mobile number");
           mobile = scan.next();
        }
        else {
            AuthenticationManager.printErrorMessage("Invalid Method");
            return;
        }
        System.out.println("Enter the new Password");
        String newPassword = scan.next();
        System.out.println("Confirm Password");
        String confirmPassowrd = scan.next();
        if(passwordValidation.apply(newPassword,confirmPassowrd)) {
            if(username != null && oldPassword!= null)
                PasswordManager.changePassword(username, oldPassword, newPassword);
            else if (mobile != null && validateMobile(mobile))
                PasswordManager.changePassword(Long.parseLong(mobile),newPassword);
        }
    }


    public static void printErrorMessage(String errorMsg)
    {
       System.out.println(errorMsg);
    }

    public static void exit()
    {
        System.out.println("Enter 'X' to exit");
       String exitInput = scan.next();
       if(exitInput.equalsIgnoreCase("x"))
       {
           return;
       }
    }

    public static void logout() {
        try {
            AuthenticationContext currentContext = AuthenticationContext.getInstance();
            if (currentContext.isAuthenticated()) {
                loggedInAccounts.remove(currentContext.getCurrentUser());
                currentContext.logoutUser();
                GlobalScanner.close();
                System.out.println("Logged Out Successfully");
            }
            else
                printErrorMessage("No active session found. Please log in first");
        }
        catch (Exception e)
        {
            throw new RuntimeException("An error occurred during logout. Please try again.");
        }
    }
}
