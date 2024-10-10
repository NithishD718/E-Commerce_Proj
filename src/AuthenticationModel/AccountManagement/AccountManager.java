package AuthenticationModel.AccountManagement;

import Util.ExcelUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class AccountManager {
    public static HashMap<String, Account> accountMapWithUser = new HashMap<>();
    public static HashMap<Long,Account> accountMapWithMobile = new HashMap<>();
    public static ArrayList<Account> loggedInAccounts = new ArrayList<>();

    private static final ExcelUtil userAccountExcel;

    static {
        try {
            userAccountExcel = new ExcelUtil("F:\\Proj\\Console Games\\E-Commerce\\src\\resources\\UserAccount.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getAccount(String username) {
        HashMap<String,Object> fetchedVendorAccount = userAccountExcel.readExcelData("VendorAccount", ExcelUtil::fetchPersitedObjects,"UserName",username);
    }

    public static void persistAccount(Account accountObject) {
        try {
            userAccountExcel.persistDataToExcel("VendorAccount", ExcelUtil::preparePersistObjects, accountObject);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
