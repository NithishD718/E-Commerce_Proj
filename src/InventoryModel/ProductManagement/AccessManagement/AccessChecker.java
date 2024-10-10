package InventoryModel.ProductManagement.AccessManagement;

import AuthenticationModel.AccountManagement.BuyerAccount;
import AuthenticationModel.AuthenticationContext;

import java.lang.reflect.Method;

public class AccessChecker {

    public static boolean checkPremiumAccess(Method method) throws IllegalAccessException {
       if(method.isAnnotationPresent(Premium.class))
       {
           if(!isPremiumUser())
               throw new IllegalAccessException("Access Denied!.Upgrade to Premium account to access this feature");
       }
       else
       {
           System.out.println("This method is not a premium method");
       }
       return true;
    }

    public static boolean checkPremiumAccessForType(Class<?> clazz) throws IllegalAccessException {
        if(clazz.isAnnotationPresent(Premium.class))
        {
            if(!isPremiumUser())
                throw new IllegalAccessException("Access Denied!.Upgrade to Premium account to access this feature");
        }
        else
        {
            System.out.println("This class is not a premium class");
        }
        return true;
    }

    public static boolean isPremiumUser()
    {
       BuyerAccount user = (BuyerAccount) AuthenticationContext.getInstance().getCurrentUser();
        return user.getRole().equals(BuyerAccount.UserRole.Premium);
    }
}
