package PurchaseModel.CartManagement;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CartConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean isCartActionInitialized;
    private Map<Integer, Method> actionMap = new HashMap<>();
    private static CartConfig instance;
    private boolean isCartEmpty;
    private Cart cart;

    public static CartConfig getInstance()
    {
        if(instance==null) {
            instance = new CartConfig();
            CartManager.storeCartConfigInCache(instance);
        }
        return instance;
    }

    public boolean isCartActionInitialized()
    {
        return isCartActionInitialized;
    }

    public Map<Integer,Method> getActionMap()
    {
        return actionMap;
    }

    public boolean isCartEmpty()
    {
       return cart.cartItems.isEmpty();
    }

    public Cart getCart()
    {
        if(cart == null)
        {
            cart = new Cart();
        }
        return cart;
    }
}
