package PurchaseModel;

import AuthenticationModel.AccountManagement.Account;
import AuthenticationModel.AccountManagement.BuyerAccount;
import AuthenticationModel.AuthenticationContext;
import InventoryModel.ProductManagement.AccessManagement.AccessChecker;
import InventoryModel.ProductManagement.AccessManagement.Premium;
import InventoryModel.ProductManagement.Product;
import PurchaseModel.OrderManagement.Order;
import PurchaseModel.OrderManagement.OrderManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PurchaseManager {

    public static void buyProduct(Product product, int quantity) {
        List<Item> purchasedOrder = new ArrayList<>();
        purchasedOrder.add(new Item(product,quantity));
       Order order = OrderManager.createOrder(purchasedOrder);
        if(order!=null)
            OrderManager.orderCheckout(order);
    }
    public static double getDiscountedPrice(double price , double discountPercent)
    {
        try {
            if(AuthenticationContext.getInstance().getCurrentUser().getAccountType() == Account.AccountType.BUYER ) {
                Method method = PurchaseManager.class.getMethod("getDiscountForPremiumUsers", double.class);
                if (AccessChecker.checkPremiumAccess(method))
                    discountPercent = (double) method.invoke(null, discountPercent);
            }
            return price * (1 - discountPercent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Premium
    public static double getDiscountForPremiumUsers(double discountPercent)
    {
        return discountPercent + 10.00;
    }

}
