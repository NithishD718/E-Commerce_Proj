package PurchaseModel.CartManagement;

import PurchaseModel.Item;
import Util.CacheManager;
import Util.GlobalScanner;
import org.apache.poi.ss.formula.CacheAreaEval;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

public class Cart {
    public static Scanner scan = GlobalScanner.getInstance();
    public static String cartConfigPath = "F:\\Proj\\Console Games\\E-Commerce\\cache\\cartConfig.txt";
    public static CacheManager cacheManager = new CacheManager(cartConfigPath);
    public static CartConfig cartConfig = (CartConfig) cacheManager.loadObjectFromCache();
    public HashMap<Integer, Item> cartItems;

    public Cart()
    {
       cartItems = new HashMap<>();
    }
    public static void manageCart(CartManager cartManager)
    {
        System.out.println("Manage Your Cart");
        System.out.println("1 --> Remove Item || 2 --> Update Quantity || 3 --> Clear Cart || 5 --> Checkout ||  6 --> View Total Price");
        int actionId = scan.nextInt();
        if(!cartConfig.isCartActionInitialized())
            initializeCartAction(cartManager);
        Method actionMethod = cartConfig.getActionMap().get(actionId);
        if(actionMethod!=null)
        {
            try {
                if (actionMethod.getParameterCount() == 0)
                    actionMethod.invoke(cartManager);
                else
                {
                    Object[] params =null;
                    if(actionId==1)
                    {
                        System.out.println("Enter the productId");
                        int productId =  scan.nextInt();
                        params = new Object[]{productId};
                    }
                    else if(actionId == 2)
                    {
                        System.out.println("Enter the productId and quantity to update");
                       int productId = scan.nextInt();
                       int quantity = scan.nextInt();
                       params = new Object[]{productId,quantity};
                    }
                    actionMethod.invoke(cartManager,params);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Invalid cart action");
        }
    }

    public static void initializeCartAction(CartManager cartManager)
    {
        try {
            cartConfig.getActionMap().put(1,  cartManager.getClass().getMethod("removeFromCart", int.class));
            cartConfig.getActionMap().put(2,  cartManager.getClass().getMethod("updateQuantity", int.class,int.class));
            cartConfig.getActionMap().put(3 , cartManager.getClass().getMethod("clearCart"));
            cartConfig.getActionMap().put(4 , cartManager.getClass().getMethod("viewCartItems"));
            cartConfig.getActionMap().put(5 , cartManager.getClass().getMethod("checkout"));
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }
}
