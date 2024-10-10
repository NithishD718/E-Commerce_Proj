import AuthenticationModel.AccountManagement.Account;
import AuthenticationModel.AuthenticationContext;
import AuthenticationModel.AuthenticationManager;
import InventoryModel.InventoryManagement.Inventory;
import PurchaseModel.PurchaseStore;
import Util.GlobalScanner;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Scanner;

public class MyStore {

    public void welcomePage() throws UserPrincipalNotFoundException {
        System.out.println("Welcome To My Store");
        Scanner scan = GlobalScanner.getInstance();
        boolean isLoggedOut = false;
        do {
            System.out.println("Please Login to Continue");
            System.out.println("1 --> Login || 2 --> Register || 3 --> Reset Password || 4 --> Logout || 5 --> Exit Product");
            int userInput = scan.nextInt();
            switch (userInput) {
                case 1:  //Login
                {
                    AuthenticationManager.login();
                    AuthenticationContext currentContext =  AuthenticationContext.getInstance();
                    if(currentContext!= null && currentContext.isAuthenticated())
                    {
                        if(currentContext.getCurrentUser().getAccountType() == Account.AccountType.VENDOR)
                             Inventory.openInventory(currentContext.getCurrentUser());
                        else
                            PurchaseStore.openStore();
                    }
                    break;
                }
                case 2: //Register
                {
                    AuthenticationManager.Register();
                    break;
                }
                case 3: //Reset Password
                {
                    System.out.println("Reset Password using : OldPassword ||  Mobile");
                    String passwordResetMethod = scan.next();
                    AuthenticationManager.resetPassword(passwordResetMethod);
                    break;
                }
                case 4:{
                    AuthenticationManager.logout();
                    isLoggedOut = true;
                    break;
                }
                case 5: {
                    GlobalScanner.close();
                    System.exit(0);
                    break;
                }
            }
        }while (!isLoggedOut);
    }
}
